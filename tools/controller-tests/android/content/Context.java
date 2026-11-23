package android.content; public class Context {
 public static final String BLUETOOTH_SERVICE="bluetooth";
 public static final int MODE_PRIVATE=0;
 public int permission; public android.bluetooth.BluetoothManager manager;
 public Context getApplicationContext(){return this;}
 public int checkSelfPermission(String name){return permission;}
 public Object getSystemService(String name){return manager;}
 public String getString(int id){return "";}
 public SharedPreferences getSharedPreferences(String name,int mode){
  return () -> new SharedPreferences.Editor(){
   public SharedPreferences.Editor putString(String key,String value){return this;}
   public void apply(){}
  };
 }
}
