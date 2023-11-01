package com.example.prm392_finalproject.main_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prm392_finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaymentFragment extends Fragment {

    public static final String TAG = PaymentFragment.class.getName();
    private TextView btnBack, tvPaymentCost;
    private EditText edtName, edtPhone, edtAddress;
    private Button btnPayment;
    private BottomNavigationView bottomNavigationView;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        btnBack = mView.findViewById(R.id.btn_back);
        btnPayment = mView.findViewById(R.id.btn_payment_mono);
        tvPaymentCost = mView.findViewById(R.id.tv_payment_cost);
        edtName = mView.findViewById(R.id.edt_name);
        edtPhone = mView.findViewById(R.id.edt_phone_num);
        edtAddress = mView.findViewById(R.id.edt_address);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Payment with momo clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Ẩn BottomNavigationView
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Hiển thị BottomNavigationView lại sau khi Fragment dừng
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}