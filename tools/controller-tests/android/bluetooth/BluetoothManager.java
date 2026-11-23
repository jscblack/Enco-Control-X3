package android.bluetooth;
import java.util.Collections; import java.util.List;
public class BluetoothManager {
 public BluetoothAdapter adapter;
 public BluetoothAdapter getAdapter(){return adapter;}
 public List<BluetoothDevice> getConnectedDevices(int profile){return Collections.emptyList();}
}
