package com.example.quoimangerapp.ui.login;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quoimangerapp.Database.AppDatabase;
import com.example.quoimangerapp.Database.Entity.User;
import com.example.quoimangerapp.MyApplication;
import com.example.quoimangerapp.R;
import com.example.quoimangerapp.sessionmanagement.SaveSharedPreferences;
import com.example.quoimangerapp.ui.MyIngredientsFragment;
import com.example.quoimangerapp.ui.register.RegisterFragment;
import com.google.android.material.navigation.NavigationView;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private View root;
    EditText emailValue, passwordValue;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = ViewModelProviders.of(this)
                .get(LoginViewModel.class);

        root = inflater.inflate(R.layout.fragment_login, container, false);

        return root;
    }
    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        final Button registerButton = root.findViewById(R.id.register);
        final Button loginButton = root.findViewById(R.id.login);
        emailValue = root.findViewById(R.id.username);
        passwordValue = root.findViewById(R.id.password);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new RegisterFragment());
                ft.commit();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailValue.getText().toString();
                final String password = passwordValue.getText().toString();
                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                //login check
                if ((TextUtils.isEmpty(email) || TextUtils.isEmpty(password))) {
                    Toast.makeText(getActivity().getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    AppDatabase db = MyApplication.getInstance().getDatabase();
                    User user = db.userDao().findByEmail(email);
                    if (user == null) {
                        Toast.makeText(getActivity().getApplicationContext(), "email inconnu", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!user.getPassword().equals(password)) {
                            Toast.makeText(getActivity().getApplicationContext(), "mot de passe incorrect", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Vous êtes connecté", Toast.LENGTH_SHORT).show();

                            SaveSharedPreferences.setLoggedIn(getActivity().getApplicationContext(),true);
                            SaveSharedPreferences.setLoggedInUserId(getActivity().getApplicationContext(), user.getUid());

                            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                            navigationView.getMenu().findItem(R.id.nav_my_ingredients).setVisible(true);
                            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.nav_host_fragment, new MyIngredientsFragment());
                            ft.commit();
                        }

                    }
                }
            }
        });

    }


}
