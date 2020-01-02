package com.example.quoimangerapp.ui.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.quoimangerapp.Database.AppDatabase;
import com.example.quoimangerapp.Database.Entity.User;
import com.example.quoimangerapp.MyApplication;
import com.example.quoimangerapp.R;
import com.example.quoimangerapp.ui.login.LoginFragment;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    EditText nameValue, surnameValue, passwordValue, emailValue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registerViewModel =
                ViewModelProviders.of(this).get(RegisterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        final Button registerButton = (Button) root.findViewById(R.id.button1);
        nameValue = root.findViewById(R.id.register_name);
        surnameValue = root.findViewById(R.id.register_surname);
        passwordValue = root.findViewById(R.id.register_password);
        emailValue = root.findViewById(R.id.register_email);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {

                final String name = nameValue.getText().toString();
                final String surname = surnameValue.getText().toString();
                final String password = passwordValue.getText().toString();
                final String email = emailValue.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)
                        || TextUtils.isEmpty(password) || TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();

                } else {
                    User user = new User();
                    user.setFirstName(surname);
                    user.setLastName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    AppDatabase db = MyApplication.getInstance().getDatabase();
                    db.userDao().insertAll(user);
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Votre compte a été créé avec succès", Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new LoginFragment());
                    ft.commit();

                }
            }
        });
    }
}