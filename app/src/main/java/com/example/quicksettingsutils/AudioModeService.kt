package com.example.quicksettingsutils

import android.graphics.drawable.Icon
import android.media.AudioManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class AudioModeService: TileService() {

    private val audioManager: AudioManager by lazy {
        getSystemService(AUDIO_SERVICE) as AudioManager
    }

    override fun onClick() {
        super.onClick()
        when(audioManager.ringerMode) {
            AudioManager.RINGER_MODE_NORMAL -> {
                activateVibrateMode()
            }
            AudioManager.RINGER_MODE_VIBRATE -> {
                activateNormalMode()
            }
        }
    }

    private fun activateNormalMode() {
        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
        qsTile.state = Tile.STATE_ACTIVE
        qsTile.icon = Icon.createWithResource(
            this,
            R.drawable.ic_baseline_volume_up_24
        )
        qsTile.updateTile()
    }

    private fun activateVibrateMode() {
        audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.icon = Icon.createWithResource(
            this,
            R.drawable.ic_baseline_vibration_24
        )
        qsTile.updateTile()
    }
}