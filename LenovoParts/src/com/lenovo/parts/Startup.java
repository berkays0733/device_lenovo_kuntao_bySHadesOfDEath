/*
* Copyright (C) 2013 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.com/licenses/>.
*
*/
package com.lenovo.parts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.preference.PreferenceManager;

import com.lenovo.parts.sound.HeadphoneGainPreference;
import com.lenovo.parts.sound.SpeakerGainPreference;
import com.lenovo.parts.sound.MicGainPreference;

public class Startup extends BroadcastReceiver {

    private void restore(String file, boolean enabled) {
        if (file == null) {
            return;
        }
        if (enabled) {
            Utils.writeValue(file, "1");
        }
    }

    private void restore(String file, String value) {
        if (file == null) {
            return;
        }
        Utils.writeValue(file, value);
    }

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        VibratorStrengthPreference.restore(context);
        WhiteTorchBrightnessPreference.restore(context);
        YellowTorchBrightnessPreference.restore(context);
        DeviceSettings.restore(context);
        String storedValue = PreferenceManager.getDefaultSharedPreferences(context).getString(DeviceSettings.S2S_KEY, "0");
        Utils.writeValue(DeviceSettings.FILE_S2S_TYPE, storedValue);
        DisplayCalibration.restore(context);
        HeadphoneGainPreference.restore(context);
        SpeakerGainPreference.restore(context);
        MicGainPreference.restore(context);
    }
}
