package com.example.cssignupapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class BottomsheetdialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    private TextView Login,error;
    EditText empid,password;
    String Sempid,Spassword;
    CheckBox check;
    String newempid,newpassword;


    public static com.example.cssignupapp.BottomsheetdialogFragment newInstance() {
        return new com.example.cssignupapp.BottomsheetdialogFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.loginpopuplayout, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Login=view.findViewById(R.id.loginbutton);
        empid=view.findViewById(R.id.edttxt_empid);
        error=view.findViewById(R.id.errorlogintext);
        password=view.findViewById(R.id.edttxt_password);
        check=view.findViewById(R.id.checkaccount);
        newempid=getActivity().getIntent().getStringExtra("empid");
        newpassword=getActivity().getIntent().getStringExtra("password");
        if (!TextUtils.isEmpty(newempid)&&!TextUtils.isEmpty(newpassword)){
            empid.setText(newempid);
            password.setText(newpassword);
        }

        SharedPreferences preferences = getActivity().getSharedPreferences("ckeckbox", Context.MODE_PRIVATE);
        String checkbox =preferences.getString("remember","");
        String Aempid = preferences.getString("name","");
        if(checkbox.equals("true")){
            Intent i = new Intent(getActivity(),HomeActivity.class);
            i.putExtra("name"," ");
            startActivity(i);
        }else if (checkbox.equals("false")){

        }

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    SharedPreferences preferences =getActivity().getSharedPreferences("ckeckbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("name",empid.getText().toString());
                    editor.putString("remember","true");
                    editor.apply();
                }else if (!buttonView.isChecked()){
                    SharedPreferences preferences =getActivity().getSharedPreferences("ckeckbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });
         Login.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 Sempid=empid.getText().toString();
                 Spassword=password.getText().toString();
                 Query checkuser = FirebaseDatabase.getInstance().getReference("Users").child(Sempid.replace(".","_"));
                 checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {

                         if(snapshot.exists()){
                             empid.setError(null);
                             empid.setEnabled(false);


                             String systempassword = snapshot.child("password").getValue(String.class);
                             String userempid = snapshot.child("userempid").getValue(String.class);
                             String Name = snapshot.child("userName").getValue(String.class);
                             String img = snapshot.child("imageURL").getValue(String.class);


                             if (systempassword != null && systempassword.equals(Spassword)){
                                 password.setError(null);
                                 password.setEnabled(false);

                                 Intent intent = new Intent(getActivity(), HomeActivity.class);
                                 intent.putExtra("img",img);
                                 intent.putExtra("name",Name);
                                 startActivity(intent);





                             }
                             else{
                                 error.setText("your password is wrong");


                             }

                         }
                         else{
                             error.setText("There is no Account with this id");
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }

                 });

             }
         });



    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }
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