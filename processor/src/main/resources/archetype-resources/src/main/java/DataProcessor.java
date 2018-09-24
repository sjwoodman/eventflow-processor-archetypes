package ${package};

import io.streamzi.cloudevents.CloudEvent;
import io.streamzi.eventflow.annotations.CloudEventComponent;
import io.streamzi.eventflow.annotations.CloudEventConsumer;
import io.streamzi.eventflow.annotations.CloudEventProducer;
import io.streamzi.eventflow.annotations.CloudEventProducerTarget;
import io.streamzi.eventflow.utils.EnvironmentResolver;

import java.util.HashMap;
import java.util.Map;

@CloudEventComponent
public class DataProcessor {
    @CloudEventProducer(name = "OUTPUT_DATA")
    CloudEventProducerTarget target;
    
    @CloudEventProducer(name = "REJECTED_DATA")
    CloudEventProducerTarget rejected;
            
    @CloudEventConsumer(name = "INPUT_DATA")
    public void onCloudEvent(final CloudEvent evt){
        final Map <String, String> contents = (HashMap)evt.getData().get();
        final Double threshold = Double.parseDouble(EnvironmentResolver.get("threshold"));
        
        if(contents.containsKey("value")){
            final Double value = Double.parseDouble(contents.get("value"));
            if(value>threshold){
                // Only send if > threshold
                target.send(evt);
            } else {
                // Reject otherwise
                rejected.send(evt);
            }
        } else {
            target.send(evt);
        }
    }
}