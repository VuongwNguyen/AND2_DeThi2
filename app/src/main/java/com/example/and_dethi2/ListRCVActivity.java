package com.example.and_dethi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.and_dethi2.Adapter.ProductsAdapter;
import com.example.and_dethi2.Dao.ProductsDAO;
import com.example.and_dethi2.Model.Products;

import java.util.List;

public class ListRCVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rcvactivity);
        RecyclerView recyclerView = findViewById(R.id.rcvProducts);

        ProductsDAO productsDAO=  new ProductsDAO(this);
        List<Products> ListPD = productsDAO.getListPD();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        ProductsAdapter adapter = new ProductsAdapter(ListPD, this,productsDAO);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}