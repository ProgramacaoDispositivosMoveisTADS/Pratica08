package br.edu.ifpe.tads.pdm.pratica08;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.GregorianCalendar;


public class MainActivity extends ActionBarActivity {


    private Button btAgendar;
    private EditText etTempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAgendar = (Button)findViewById(R.id.button);
        etTempo = (EditText)findViewById(R.id.editText);
        btAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleAlarm();
            }
        });


    }

    public void scheduleAlarm(){

        // Tempo atual mais 10 segundos (10 * 1000 milissegundos)
        //Long time = new GregorianCalendar().getTimeInMillis()+10*1000;
        int timeSeconds = Integer.parseInt(etTempo.getText().toString());
        Long time = new GregorianCalendar().getTimeInMillis()+timeSeconds*1000;
        // Cria Intent do alarm, que será recebido pela classe AlarmReceiver
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        // Cria um PendingIntent, usado para fazer o broadcast do alarme
        PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(
                this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
        //Obtem o gerenciador de alarmes do sistema
        AlarmManager alarmManager =
                (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //Configura um alarme lançará o intent em 10 segundos
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingAlarmIntent);
        Toast.makeText(this, "Alarme agendado.", Toast.LENGTH_LONG).show();
        // Finaliza a atividade
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
