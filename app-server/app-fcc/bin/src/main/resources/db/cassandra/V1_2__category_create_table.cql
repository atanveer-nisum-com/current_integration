CREATE TABLE IF NOT EXISTS app_list(
type text,
key text,
value SET<text>,
PRIMARY KEY (key)
);