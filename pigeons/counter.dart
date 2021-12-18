import 'package:pigeon/pigeon.dart';

// @FlutterApi()
// abstract class FlutterCounterApi {
//   void setValue(int value);
//   int getValue();
// }

@HostApi()
abstract class CounterApi {
  void setCounter(int counterValue);
  int getCounter();
}
