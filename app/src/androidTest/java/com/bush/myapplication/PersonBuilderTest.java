package com.bush.myapplication;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.bush.myapplication.person.builder.PersonBuilder;
import com.bush.myapplication.person.exception.AccidentRateException;
import com.bush.myapplication.person.exception.AgeDriverException;
import com.bush.myapplication.person.exception.DrivingLicenseException;
import com.bush.myapplication.person.exception.NameDriverException;
import com.bush.myapplication.person.exception.SurnameDriverException;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PersonBuilderTest {
    @Test(expected = DrivingLicenseException.class)
    public void TestDlBeforeBirthday() throws NameDriverException, SurnameDriverException, AgeDriverException, AccidentRateException, DrivingLicenseException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        new PersonBuilder()
                .SetName("Ivan")
                .SetSurname("Ivanov")
                .SetAge("02.01.2004")
                .SetDateLicenseRelease("01.01.2022")
                .SetRegion(54)
                .SetCity(0)
                .SetAccidentRate(0.56f)
                .Build(context);
    }

    @Test
    public void testDlAfterBirthday() throws AgeDriverException, DrivingLicenseException, NameDriverException, SurnameDriverException, AccidentRateException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        new PersonBuilder()
                .SetName("Ivan")
                .SetSurname("Ivanov")
                .SetAge("01.01.2004")
                .SetDateLicenseRelease("02.01.2022")
                .SetRegion(54)
                .SetCity(0)
                .SetAccidentRate(0.56f)
                .Build(context);
    }

    @Test(expected = AgeDriverException.class)
    public void testFutureBirthday() throws AgeDriverException, DrivingLicenseException, NameDriverException, SurnameDriverException, AccidentRateException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        new PersonBuilder()
                .SetName("Ivan")
                .SetSurname("Ivanov")
                .SetAge("01.01.2125")
                .SetDateLicenseRelease("03.02.2025")
                .SetRegion(54)
                .SetCity(0)
                .SetAccidentRate(0.56f)
                .Build(context);
    }
    @Test(expected = DrivingLicenseException.class)
    public void testFutureDlRelease() throws AgeDriverException, DrivingLicenseException, NameDriverException, SurnameDriverException, AccidentRateException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        new PersonBuilder()
                .SetName("Ivan")
                .SetSurname("Ivanov")
                .SetAge("01.01.2004")
                .SetDateLicenseRelease("03.02.2125")
                .SetRegion(54)
                .SetCity(0)
                .SetAccidentRate(0.56f)
                .Build(context);
    }
}
