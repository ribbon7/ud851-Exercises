package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //TODO (3) Create a data binding instance called mBinding of type ActivityMainBinding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO (4) Set the Content View using DataBindingUtil to the activity_main layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // TODO (5) Load a BoardingPassInfo object with fake data using FakeDataUtils
        BoardingPassInfo passInfo = FakeDataUtils.generateFakeBoardingPassInfo();

        // TODO (9) Call displayBoardingPassInfo and pass the fake BoardingInfo instance
        displayBoardingPassInfo(passInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        // TODO (6) Use mBinding to set the Text in all the textViews using the data in info
        binding.textViewDestinationAirport.setText(info.destCode);
        binding.textViewFlightCode.setText(info.flightCode);
        binding.textViewGate.setText(info.departureGate);
        binding.textViewOriginAirport.setText(info.originCode);
        binding.textViewPassengerName.setText(info.passengerName);
        binding.textViewSeat.setText(info.seatNumber);
        binding.textViewTerminal.setText(info.departureTerminal);
        // TODO (7) Use a SimpleDateFormat formatter to set the formatted value in time text views
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        binding.textViewBoardingTime.setText(boardingTime);
        binding.textViewDepartureTime.setText(departureTime);
        binding.textViewArrivalTime.setText(arrivalTime);
        // TODO (8) Use TimeUnit methods to format the total minutes until boarding
        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String timeUntilBoarding = getString(R.string.countDownFormat, hoursUntilBoarding, minutesLessHoursUntilBoarding);

        binding.textViewBoardingInCountdown.setText(timeUntilBoarding);
    }
}

