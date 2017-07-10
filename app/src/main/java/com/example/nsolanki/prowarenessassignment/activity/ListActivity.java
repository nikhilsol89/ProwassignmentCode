package com.example.nsolanki.prowarenessassignment.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.nsolanki.prowarenessassignment.R;
import com.example.nsolanki.prowarenessassignment.adapters.NamesListRecyclerAdapter;
import com.example.nsolanki.prowarenessassignment.database.SalesDbManager;
import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.enums.ResponseTypeEnum;
import com.example.nsolanki.prowarenessassignment.interfaces.DeleteButtonListener;
import com.example.nsolanki.prowarenessassignment.interfaces.OnErrorReceived;
import com.example.nsolanki.prowarenessassignment.interfaces.OnResponseReceived;
import com.example.nsolanki.prowarenessassignment.interfaces.UpdateListener;
import com.example.nsolanki.prowarenessassignment.model.BaseDataModel;
import com.example.nsolanki.prowarenessassignment.model.ContactListModel;
import com.example.nsolanki.prowarenessassignment.request.RequestDispatcher;
import com.example.nsolanki.prowarenessassignment.request.RequestEnums;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class ListActivity extends AppCompatActivity implements OnResponseReceived, OnErrorReceived, DeleteButtonListener,UpdateListener {
    public RecyclerView myRecyclerView;
    private NamesListRecyclerAdapter adapter;
    private RequestDispatcher requestDispatcher;
    ContactListModel listModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);

        getListOfNames(true);
    }

    private void getListOfNames(boolean isNetworkAvailable) {
        requestDispatcher = RequestDispatcher.getInstance();
        requestDispatcher.setInputParamsObject(null);
        requestDispatcher.setModelEnums(ModelEnums.NAMES_LIST);
        requestDispatcher.setRequestEnum(RequestEnums.NETWORK);
        requestDispatcher.setResponseTypeEnums(ResponseTypeEnum.JSON_OBJECT);
        requestDispatcher.setNetworkAvailable(isNetworkAvailable);
        requestDispatcher.setRequestType(Request.Method.POST);

        String url = "http://139.162.152.157/contactlist.php";
        requestDispatcher.setWebUrl(url);
        requestDispatcher.dispatchRequest(this, this, this);
    }


    @Override
    public void onErrorReceived(VolleyError error) {
        Toast.makeText(this, "SOme Error Occured while fetching data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void responseReceived(BaseDataModel model) {
        if (model instanceof ContactListModel) {
            listModel = (ContactListModel) model;

            if (listModel.getStatus().equalsIgnoreCase("success")) {
                Log.e("nikhil", "Inside success");

                if (SalesDbManager.getInstance(this).getCount() <= 0) {
                    SalesDbManager.getInstance(this).insertContactsList(listModel.getContactDetailsModelList());
                    drawList(true);
                } else {
                    drawList(false);
                }
            }
        }
    }


    @Override
    public void onItemClickedForDelete(int position) {
        Log.e("nikhil", "Item clicked for delete" + position);
        SalesDbManager.getInstance(this).updateContactList(listModel.getContactDetailsModelList().get(position),this);
        //drawList(false);
    }

    public void drawList(boolean isFirstTime) {
        if (isFirstTime) {
            adapter = new NamesListRecyclerAdapter(this, listModel.getContactDetailsModelList(), this);
            myRecyclerView.setAdapter(adapter);
        } else {
            adapter = new NamesListRecyclerAdapter(this, SalesDbManager.getInstance(this).getContactDetailsModelList(), this);
            myRecyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void updateListener(boolean isUpdated) {
        Log.e("nikhil","Listener called");
        if(isUpdated){
            adapter = new NamesListRecyclerAdapter(this, SalesDbManager.getInstance(this).getContactDetailsModelList(), this);
            myRecyclerView.setAdapter(adapter);
        }
    }
}
