package com.bush.myapplication.insurance.buttons;

import android.view.View;

import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.databinding.InsuranceFragmentBinding;
import com.bush.myapplication.insurance.InsuranceFragment;

import java.time.LocalDate;

public class InsuranceRightButtonHandler implements ClickHandler, View.OnClickListener
{
    private InsuranceFragmentBinding binding;
    private InsuranceFragment fragment;

    public InsuranceRightButtonHandler(InsuranceFragment fragment, InsuranceFragmentBinding binding) {
        this.fragment = fragment;
        this.binding = binding;
    }

    @Override
    public void onClick(View view)
    {
        OnClickHandler();
    }

    @Override
    public void OnClickHandler()
    {
        MTPL.GetInstance().setDriversLimit(
                PearseBooleanValue(binding.spinnerUsers.getSelectedItemPosition()));
        MTPL.GetInstance().setCarPeriod(binding.spinnerPer.getSelectedItemPosition());

        NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_insuranceFragment_to_resultFragment);
//        if (MTPL.GetInstance().getPersonList().size() > 0)
//            NavHostFragment.findNavController(fragment)
//                    .navigate(R.id.action_insuranceFragment_to_resultFragment);
//        else
//            NavHostFragment.findNavController(fragment)
//                    .navigate(R.id.action_insuranceFragment_to_newPersonFragment);
    }

    @Override
    public int ParseIntegerText(String str) {
        return 0;
    }

    @Override
    public float ParseFloatText(String str) {
        return 0;
    }

    @Override
    public boolean PearseBooleanValue(String str)
    {
        return false;
    }

    @Override
    public boolean PearseBooleanValue(int value)
    {
        if (binding.spinnerUsers.getSelectedItemPosition() == 0)
            return false;
        return true;
    }

    @Override
    public LocalDate ParseDate(String str) {
        return null;
    }
}
