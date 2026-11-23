package android.content;

/** Minimal SharedPreferences surface used by EncoController. */
public interface SharedPreferences {
    Editor edit();

    interface Editor {
        Editor putString(String key, String value);

        void apply();
    }
}
