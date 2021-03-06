package com.headbangers.mi.activity;

import roboguice.activity.GuiceListActivity;
import roboguice.inject.InjectResource;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.headbangers.mi.R;

public class MainActivity extends GuiceListActivity {

    @InjectResource(R.array.main_menu)
    protected String[] menus;

    protected int[] icons = { R.drawable.grapp01, R.drawable.pin01,
            R.drawable.grapp02, R.drawable.pin02, R.drawable.grapp03,
            R.drawable.pin03, R.drawable.grapp04, R.drawable.pin04,
            R.drawable.grapp05, R.drawable.pin05, R.drawable.grapp06,
            R.drawable.pin06 };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setListAdapter(new MainMenuAdapter(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MediaPlayer player = RadioActivity.mediaPlayer;
        if (player.isPlaying()) {
            player.stop();
            player.release();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent gotoPage = new Intent(this, RadioActivity.class);
        startActivity(gotoPage);
    }

    class MainMenuAdapter extends ArrayAdapter<String> {

        private Activity context;

        public MainMenuAdapter(Activity context) {
            super(context, R.layout.main_menu_item, menus);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                row = inflater.inflate(R.layout.main_menu_item, null);
            }

            TextView label = (TextView) row.findViewById(R.id.menuLabel);
            TextView disclaimer = (TextView) row
                    .findViewById(R.id.menuDisclaimer);
            ImageView icon = (ImageView) row.findViewById(R.id.menuIcon);

            String[] menuDefinition = menus[position].split("_");

            label.setText(menuDefinition[0]);
            disclaimer.setText(menuDefinition[1]);

            icon.setImageResource(icons[position]);

            return row;
        }
    }
}
