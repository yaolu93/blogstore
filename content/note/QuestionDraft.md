```
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
