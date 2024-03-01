Mockito 详解：
https://pdai.tech/md/develop/ut/dev-ut-x-mockito.html  

```Java
        if (partialTopic != null) {
            KStream<String, GenericRecord>[] partialBranch = stream
                    .branch((k, v) -> {
                                Map<Utf8, Utf8> metrics = Optional.ofNullable((Map) v.get(METRIC)).orElse(new HashMap<>());
                                return metrics.containsKey(new Utf8(PARTIAL));
                            }
                            , (k, v) -> true);

            partialBranch[0]
                    .process(() -> new Processor<String, GenericRecord, String, GenericRecord>() {
                        ProcessorContext context;

                        @Override
                        public void init(ProcessorContext context) {
                            this.context = context;
                        }

                        @Override
                        public void process(Record<String, GenericRecord> record) {
                            context.forward(record.withTimestamp(context.currentSystemTimeMs()));
                        }
                    })
                    .to(partialTopic);
            stream = partialBranch[1];
        }

```


```Java

 @Override
    @SuppressWarnings("unchecked")
    public KStream<String, GenericRecord> apply(KStream<String, GenericRecord> stream) {
        KStream<String, GenericRecord>[] postBranch = stream
                .transform(() -> new MappingTransformer(configsBySchemaName))
                .branch((k, v) -> isErrorRecord(v), (k, v) -> true);
        if (mappingStageConfig.hasErrorTopic()) {
            postBranch[0].mapValues(this::unmarkErrorRecord).to(mappingStageConfig.errorTopic);
        }
        return postBranch[1];
    }


```Java
         @Test
            public void testProduceWithAvroSerializer() throws NoSuchFieldException, IllegalAccessException {
        
                // Set up the test data
                settings = this.settings();
                SinkRecord sinkRecord = getTestRecord();
                List<SinkRecord> sinkRecords = Collections.singletonList(sinkRecord);
        
                AvroData avroData = mock(AvroData.class);
                java.lang.reflect.Field avroDataField = KafkaSinkTask.class.getDeclaredField("avroData");
                avroDataField.setAccessible(true);
                avroDataField.set(task, avroData);
        
                task.start(settings);
        
                task.put(sinkRecords);
        
                // Mock the necessary dependencies
                when(config.getOutputTopic()).thenReturn("outputTopic");
                when(avroProducer.send(any(ProducerRecord.class))).thenReturn("hello");
        
        
                // Call the method under test
        //        verify(avroProducer, times(1)).send(any());
        
                Assertions.assertEquals(avroProducer.send(any()), null);
            }

### lambda 表達式：
https://zhuanlan.zhihu.com/p/112771403 

```
