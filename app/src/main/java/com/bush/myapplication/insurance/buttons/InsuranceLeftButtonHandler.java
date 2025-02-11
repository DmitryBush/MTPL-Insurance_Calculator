package com.bush.myapplication.insurance.buttons;

import android.view.View;

import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.databinding.InsuranceFragmentBinding;
import com.bush.myapplication.insurance.InsuranceFragment;

import java.util.Calendar;

public class InsuranceLeftButtonHandler implements ClickHandler, View.OnClickListener
{
    private InsuranceFragmentBinding binding;
    private InsuranceFragment fragment;

    public InsuranceLeftButtonHandler(InsuranceFragment fragment, InsuranceFragmentBinding binding)
    {
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
        var mtpl = MTPL.getInstance();
        mtpl.setDriversLimit(PearseBooleanValue(binding.spinnerUsers
                .getSelectedItemPosition()));
        mtpl.setCarPeriod(binding.spinnerPer.getSelectedItemPosition());

        if (mtpl.getPersonListSize() > 0)
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_insuranceFragment_to_personListFragment);
        else
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_insuranceFragment_to_personFragment);
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
    public Calendar ParseDate(String str) {
        return null;
    }
}
