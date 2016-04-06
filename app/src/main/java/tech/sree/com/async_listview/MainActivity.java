package tech.sree.com.async_listview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] names={"A","B","C","D","E","F",
            "AA","BA","CA","DA","EA","FA",
            "AB","BB","CB","DB","EB","FB",
            "AC","BC","CC","DC","EC","FC",
            "AD","BD","CD","DD","ED","FD",
            "AE","BE","CE","DE","EE","FE",
            "AF","BF","CF","DF","EF","FF"};
    ProgressBar progressBar;
    ListView listView;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        editText1 = (EditText)findViewById(R.id.editText);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(arrayAdapter);
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void,String,String>{ //Params, Progress, Result
        ArrayAdapter<String> adapter;
        int count ;
        @Override
        protected void onPreExecute() {

            adapter = (ArrayAdapter<String>)listView.getAdapter();
               progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(names.length);
            count = 0 ;
            progressBar.setProgress(count);

        }

        @Override
        protected String doInBackground(Void... params) {
            for(String info : names) {
                publishProgress(info);
                try {
                   // wait(100);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "All Name are added Successfully";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            editText1.append("  "+values[0]+"-");
          //  editText1.setText(values[0]);
            count++;
            progressBar.setProgress(count);

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),"All Name are added Successfully",Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);

        }
    }
}
