package dnk.thermo;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.*;


public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
           {
                RefreshTemper();
            }
        });
        RefreshTemper(); // при запуске грузим температуру сразу       
    }

    public String GetTemper(String urlsite){ // фукция загрузки температуры
         String matchtemper = "";
         try { // загрузка страницы
            URL url = new URL(urlsite);
             URLConnection conn = url.openConnection();
             InputStreamReader rd = new InputStreamReader(conn.getInputStream());
             StringBuilder allpage = new StringBuilder();
             int n = 0;
             char[] buffer = new char[40000];
             while (n >= 0) {
                 n = rd.read(buffer, 0, buffer.length);
                 if (n > 0) {allpage.append(buffer, 0, n);}
             }
             System.out.println("www");
             // работаем с регулярками
            final Pattern pattern = Pattern.compile
            		("<span");
             Matcher matcher = pattern.matcher(allpage.toString());
             if (matcher.find()) {matchtemper = matcher.group(1);}        
             return matchtemper;
         }
         catch (Exception e) {System.out.println("error"); }
         return matchtemper;  
     };

     public void RefreshTemper() { 
         final TextView tTemper = (TextView) findViewById(R.id.textView1);
          String bashtemp = "";
           bashtemp = GetTemper("http://alupkaresort.narod.ru/");
         tTemper.setText(bashtemp.concat("°")); // отображение температуры
    };
     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
