package com.cranestylelabs.danhoffman.listviewsample.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.cranestylelabs.danhoffman.listviewsample.network.ConnectionMonitor;
import com.cranestylelabs.danhoffman.listviewsample.network.DownloadWebpageTask;
import com.cranestylelabs.danhoffman.listviewsample.adapters.FilterAdapter;
import com.cranestylelabs.danhoffman.listviewsample.R;



public class ComplexListViewActivity extends ActionBarActivity {

    private FilterAdapter mFilterAdapter;
    private EditText mTextEntry;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_list_view);

        mFilterAdapter = new FilterAdapter(this);
        mListView = (ListView)findViewById(R.id.listView1);
        mListView.setAdapter(mFilterAdapter);

        mTextEntry = (EditText)findViewById(R.id.text_entry);
        mTextEntry.addTextChangedListener(searchTextWatcher);

        this.getDataFromServer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // ignore
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
        }

        @Override
        public void afterTextChanged(Editable s) {
            stripNewLine(s);
            mFilterAdapter.getFilter().filter(s.toString());
        }

        private void stripNewLine(Editable s) {
            for(int i = s.length(); i > 0; i--){
                if(s.subSequence(i-1, i).toString().equals("\n"))
                    s.replace(i-1, i, "");

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_complex_list_view, menu);
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

    public void getDataFromServer() {
        if(ConnectionMonitor.hasNetworkConnection(this.getApplicationContext())){
            String url = this.scrubDataServerIp(getApplicationContext().getString(R.string.data_server_ip));
            new DownloadWebpageTask(mFilterAdapter).execute(url);
        }
    }

    private String scrubDataServerIp(String ip) {
        ip = ip.replace("http", "").replace("file", "").replace("//:", "");
        return "http://" + ip;
    }

}
