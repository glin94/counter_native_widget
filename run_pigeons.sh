flutter pub run pigeon \
  --input pigeons/counter.dart \
  --dart_out lib/counter_api.dart \
  --objc_header_out ios/Runner/counter.h \
  --objc_source_out ios/Runner/counter.m \
  --java_out ./android/app/src/main/java/io/flutter/plugins/Counter.java \
  --java_package "io.flutter.plugins"