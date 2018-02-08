package com.nisum.common.message.consumer;

import com.nisum.common.event.message.dto.EtaEvent;

public interface BaseMessageConsumer {

	public void processEvent(EtaEvent event);
}
