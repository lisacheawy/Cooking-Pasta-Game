package ch.makery.address
import ch.makery.address.model.MenuBarModel
import ch.makery.address.view.MainMenu
import ch.makery.address.view.DishSelection
import ch.makery.address.view.Kitchen
import ch.makery.address.view.Fridge
import ch.makery.address.view.Countertop
import ch.makery.address.view.Stovetop
import ch.makery.address.view.Completed
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.scene.image.Image
import scala.collection.mutable.ListBuffer
import scalafx.scene.text.Text


object MainApp extends JFXApp {
  var sceneCategoryCounter = 0

  var counter = new ListBuffer[String]().toSet //selected ingredients
  var checklistItem: ListBuffer[Text] = new ListBuffer[Text]()

  val bgMusic = new Media(getClass.getResource("/firefly.mp3").toExternalForm())
  val bgMusicPlayer = new MediaPlayer(bgMusic){
    volume = 0.2
    cycleCount = 3
  }
  bgMusicPlayer.play()

  val menuBar = new MenuBarModel()

  stage = new PrimaryStage {
    title = "Cooking Pasta Game"
    resizable = false
    scene = new Scene(1220, 880){
    }
  }
  
  stage.getIcons().add(new Image(getClass.getResourceAsStream("/icon.png")))

  sceneCategory()
  def sceneCategory() = {
    sceneCategoryCounter match {
      case 0 => new MainMenu().mainMenu(stage, menuBar.menuBar)
      case 1 => new DishSelection().dishSelection(stage, menuBar.menuBar)
      case 2 => new Kitchen().kitchen(stage, menuBar.menuBar)
      case 3 => new Fridge().fridge(stage, menuBar.menuBar)
      case 4 => new Countertop().counterTop(stage, menuBar.menuBar)
      case 5 => new Stovetop().stoveTop(stage, menuBar.menuBar)
      case 6 => new Completed().completed(stage, menuBar.menuBar)
    }
  }

}