# Chef Recipe to be used in --local-mode to install and configure all pre-requisites of ecommerce app.
# ====================================================================================================
#

# Service account name
service_account = 'appdeployment'

# Exit if this recipe is not executed with root user
if ENV['USER'] != 'root'
  Chef::Application.fatal!("Run this script as root user.", 42)
end

# Create a new user for service account
user "#{service_account}" do
  comment 'Creating a service account for ETA deployment.'
end

# Create directoy for service account ssh key pair
directory "/home/#{service_account}/.ssh" do
  owner "#{service_account}"
  group "#{service_account}"
  mode '0700'
  action :create
end

remote_file "Copy private key to service account" do
  path "/home/#{service_account}/.ssh/id_rsa"
  source "file:///root/.ssh/id_rsa"
  owner "#{service_account}"
  group "#{service_account}"
  mode '0700'
end

# Install docker dependencies
package ['yum-utils', 'device-mapper-persistent-data', 'lvm2']

yum_repository 'docker-ce-stable' do
  description 'Docker CE Stable - $basearch'
  baseurl 'https://download.docker.com/linux/centos/7/$basearch/stable'
  enabled true
  gpgkey 'https://download.docker.com/linux/centos/gpg'
  gpgcheck true
  action :create
end

package 'docker-ce'

# Add service account to docker group
group 'docker' do
  action :modify
  members "#{service_account}"
  append true
end

# Install docker-compose dependencies
package 'epel-release'
package 'python-pip'

execute 'install docker-compose through pip' do
  command 'pip install docker-compose'
end

# Install and configure JDK
directory '/opt/tools' do
  owner "#{service_account}"
  group "#{service_account}"
  mode '0755'
  action :create
end

bash 'Download JDK' do
  user "#{service_account}"
  cwd '/opt/tools/'
  code <<-EOC
  wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u162-b12/0da788060d494f5095bf8624735fa2f1/jdk-8u162-linux-x64.tar.gz
  EOC
end

execute 'Extract tar.gz file: jdk-8u151-linux-x64.tar.gz' do
  command 'tar xzf /opt/tools/jdk-8u151-linux-x64.tar.gz'
  cwd '/opt/tools/jdk1.8.0_151'
  not_if { File.directory?("/opt/tools/jdk1.8.0_151") }
end

link '/opt/tools/java' do
  to '/opt/tools/jdk1.8.0_151'
  link_type :symbolic
end

bash_profile_file = Chef::Util::FileEdit.new("/home/#{service_account}/.bash_profile")
java_home_var = 'JAVA_HOME=/opt/tools/java'
bash_profile_file.insert_line_if_no_match(/#{java_home_var}/, java_home_var)
path_var = 'PATH=$PATH:$JAVA_HOME/bin'
bash_profile_file.insert_line_if_no_match(/#{path_var}/, path_var)

# Install maven package
package ['maven', 'dos2unix']

directory "/home/#{service_account}/repo" do
  owner "#{service_account}"
  group "#{service_account}"
  mode '0755'
  action :create
end

directory "/home/#{service_account}/scripts" do
  owner "#{service_account}"
  group "#{service_account}"
  mode '0755'
  action :create
end

bash 'Configure repository inside service account' do
  user "#{service_account}"
  cwd "/home/#{service_account}/repo"
  code <<-EOC
  git clone git@github.com:nisum-inc/training-ecommerce-project.git
  EOC
  not_if { File.directory?("/home/#{service_account}/repo/training-ecommerce-project") }
end

bash 'Copy deployment scripts from repository' do
  user "#{service_account}"
  cwd "/home/#{service_account}/repo/training-ecommerce-project"
  code <<-EOC
  git checkout sprint-16
  git reset --hard
  git pull
  cp -v -r /home/#{service_account}/repo/training-ecommerce-project/install/build /home/#{service_account}/scripts/
  cp -v -r /home/#{service_account}/repo/training-ecommerce-project/install/deploy /home/#{service_account}/scripts/
  EOC
end

remote_directory "/home/#{service_account}/scripts/deploy/common/" do
  source 'file:///opt/tools/jdk1.8.0_151/'
  owner "#{service_account}"
  group "#{service_account}"
  mode '0755'
  recursive true
  action :create
  not_if { File.directory?("/home/#{service_account}/scripts/deploy/common/") }
end

service 'docker' do
  supports :status => true, :restart => true, :reload => true
  action [ :enable, :start ]
end
