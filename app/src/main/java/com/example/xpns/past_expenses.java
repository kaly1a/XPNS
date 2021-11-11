package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class past_expenses extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_expenses);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.pastExp);
        //Query
        Query query = firebaseFirestore.collection("expenses").whereEqualTo("userID",userID);


        //Recycler Option
        FirestoreRecyclerOptions<BillsModel> options = new FirestoreRecyclerOptions.Builder<BillsModel>()
                .setQuery(query, BillsModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<BillsModel, BillsViewHolder>(options) {
            @NonNull
            @Override
            public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new BillsViewHolder(view);
            }



            @Override
            protected void onBindViewHolder(@NonNull BillsViewHolder holder, int position, @NonNull BillsModel model) {
                holder.b_date.setText(model.getExpenseDate());
                holder.b_amount.setText(model.getExpenseAmount());
                holder.b_desc.setText(model.getExpenseDescription());



            }



        };


        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager( new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);

    }

    private class BillsViewHolder extends RecyclerView.ViewHolder{
        private TextView b_date;
        private TextView b_desc;
        private TextView b_amount;

        public BillsViewHolder(@NonNull View itemView) {
            super(itemView);

            b_date = itemView.findViewById(R.id.b_date);
            b_desc = itemView.findViewById(R.id.b_desc);
            b_amount = itemView.findViewById(R.id.b_amount);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}