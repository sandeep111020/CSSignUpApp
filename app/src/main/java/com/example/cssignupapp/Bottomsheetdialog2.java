package com.example.cssignupapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Bottomsheetdialog2 extends BottomSheetDialogFragment
        implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    private TextView Reg,errortext;

    EditText Empid, password,CheckPassword;

    String Sempid,Spassword,Scheckpassword;
    Context context;

    public static com.example.cssignupapp.Bottomsheetdialog2 newInstance() {
        return new com.example.cssignupapp.Bottomsheetdialog2();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.regsiterpopuplayout, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Reg=view.findViewById(R.id.loginbutton);
        Empid=view.findViewById(R.id.edttxt_empid);
        errortext=view.findViewById(R.id.errortext);
        password=view.findViewById(R.id.edttxt_password);
        CheckPassword=view.findViewById(R.id.edttxt_password_check);


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sempid=Empid.getText().toString();
                Spassword=password.getText().toString();
                Scheckpassword=CheckPassword.getText().toString();

                if (TextUtils.isEmpty(Sempid)){
                    errortext.setText("Please enter Employee id");
                }
                else if (TextUtils.isEmpty(Spassword)){
                    errortext.setText("Please enter password");
                }
                else if (TextUtils.isEmpty(Scheckpassword)){
                    errortext.setText("Please enter check password");

                }else if(!Sempid.contains(".com")){
                    errortext.setText("Please Enter Vaild Email");
                }else if (Scheckpassword.equals(Spassword)){


//                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
//                    DatabaseReference reference = rootNode.getReference("Accounts");
//
//
//                    UserAccount addnewUser = new UserAccount(Sempid,Spassword);
//                    reference.child(Sempid).setValue(addnewUser);
                    Intent i = new Intent(getActivity(),NewActivity.class);
                    i.putExtra("empid",Sempid);
                    i.putExtra("password",Spassword);
                    startActivity(i);

                }
                else{
                    errortext.setText("Please enter same passwords in two fields");
                }


            }
        });



    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onClick(View view) {
        TextView tvSelected = (TextView) view;
        mListener.onItemClick(tvSelected.getText().toString());
        dismiss();
    }
    public interface ItemClickListener {
        void onItemClick(String item);
    }
}