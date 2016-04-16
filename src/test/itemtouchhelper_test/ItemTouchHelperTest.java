package test.itemtouchhelper_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemTouchHelperTest extends AppCompatActivity {

    private RecyclerView sPackageListView;
    private MyListAdapter sPackageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_list);
        sPackageListView = (RecyclerView) findViewById(R.id.barcode_list);

        // sPackageListView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        sPackageListView.setLayoutManager(mLayoutManager);
        sPackageListAdapter = new MyListAdapter();
        sPackageListView.setAdapter(sPackageListAdapter);

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(sPackageListView);
    }

    private ItemTouchHelper itemTouchHelper;

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
        }
    };

    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.BarcodeViewHolder> {

        ArrayList<String> items = new ArrayList<String>();

        public MyListAdapter() {
            for (int i = 1; i <= 10; i++)
                items.add("Item " + i);
        }

        public class BarcodeViewHolder extends RecyclerView.ViewHolder {
            public TextView mView;
            public BarcodeViewHolder(TextView v) {
                super(v);
                mView = v;
            }
        }

        @Override
        public BarcodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemTouchHelper.attachToRecyclerView(null);
                    return false;
                }
            });
            return new BarcodeViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(final BarcodeViewHolder holder, final int position) {
            holder.mView.setTextSize(26);
            holder.mView.setPadding(10, 20, 10, 20);
            holder.mView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
