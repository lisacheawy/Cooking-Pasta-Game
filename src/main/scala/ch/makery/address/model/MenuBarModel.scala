package ch.makery.address.model
import ch.makery.address.MainApp
import scalafx.scene.control.{MenuBar, Menu, MenuItem}
import scalafx.Includes._

class MenuBarModel {
    var musicPlaying = true
    
    val menuBar = new MenuBar()
    val options = new Menu("Options")
    val muteMusic = new MenuItem("Mute music")

    muteMusic.onAction = handle {
        if(musicPlaying){
        MainApp.bgMusicPlayer.pause()
        musicPlaying = false
        muteMusic.text = "Unmute music"
        }
        else if(!musicPlaying){
            MainApp.bgMusicPlayer.play()
            musicPlaying = true
            muteMusic.text = "Mute music"
        }
    }
    menuBar.menus = List(options)
    options.items = List(muteMusic)
}
