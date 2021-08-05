package ch.makery.address.view

import ch.makery.address.model.ChecklistModel
import ch.makery.address.MainApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._ //implicit
import scalafx.scene.control.MenuBar
import scala.collection.mutable.ListBuffer
import scalafx.scene.text.{Font, Text}
import scalafx.scene.paint.Color
import scalafx.animation._

class Kitchen {
  val controller = new KitchenController()

  def kitchen(stage: PrimaryStage, menuBar: MenuBar): Unit = {
    val scene = new Scene {

      content = new ListBuffer()
      content += (controller.model.kitchenBackgroundImageView, controller.model.fridgeImageView, controller.checklistModel.checklistIconImageView)
      controller.model.kitchenIngredients.keys.foreach { i =>
        content += controller.model.kitchenIngredients(i)
        if (MainApp.counter.contains(i)) {
          content -= controller.model.kitchenIngredients(i)
        }
        controller.model.kitchenIngredients(i).onMousePressed = handle {
          controller.model.pop.play()
          content -= controller.model.kitchenIngredients(i)
          MainApp.counter += i
        }
      }

      var animationTimer: AnimationTimer = AnimationTimer(t => {
        if (controller.model.timer > 0) {
          val second = (t - controller.model.timer) / 1e9
          if (MainApp.counter.size == 11) {
            controller.model.isFinished = true
            if (!content.contains(controller.checklistModel.allCollectedImageView)) {
              content += controller.checklistModel.allCollectedImageView
              content -= controller.checklistModel.checklistImageView
              content -= controller.checklistModel.collectText
              content -= controller.model.inKitchen
              for (i <- MainApp.checklistItem) {
                content -= i
              }
            }
            if (controller.model.isFinished) {
              controller.model.sceneTimer -= second
            }
            if (controller.model.sceneTimer <= 0) {
              animationTimer.stop
              MainApp.sceneCategoryCounter = 4
              MainApp.sceneCategory()
            }
          }

          controller.model.fridgeImageView.onMousePressed = handle {
            content -= controller.checklistModel.checklistImageView
            for (i <- MainApp.checklistItem) {
              content -= i
            }
            MainApp.checklistItem.clear()
            animationTimer.stop
            MainApp.sceneCategoryCounter = 3
            MainApp.sceneCategory()
          }

          controller.checklistModel.checklistIconImageView.onMousePressed = handle {
            if (controller.model.counterClick % 2 == 0) { //Close checklist
              controller.model.kitchenIngredients.values.foreach { i =>
                i.setDisable(false)
              }
              content -= controller.checklistModel.checklistImageView
              content -= controller.checklistModel.collectText
              content -= controller.model.inKitchen
              for (i <- MainApp.checklistItem) {
                content -= i
              }
              MainApp.checklistItem.clear()
            }
            else { //Open checklist
              controller.model.pageFlip.play()
              content += controller.checklistModel.checklistImageView
              content += controller.checklistModel.collectText
              content += controller.model.inKitchen
              var y = 350
              controller.model.kitchenIngredients.keys.foreach { i =>
                controller.model.kitchenIngredients(i).setDisable(true)
                if (MainApp.counter.contains(i)) {
                  MainApp.checklistItem += new Text(420, y, i) {
                    font = controller.checklistModel.fontStyle3
                    fill = Color.Green
                  }
                }
                else {
                  MainApp.checklistItem += new Text(420, y, i) {
                    font = controller.checklistModel.fontStyle3
                    fill = Color.Black
                  }
                }
                y += 40
              }

              for (i <- MainApp.checklistItem) {
                content += i
              }
            }
            controller.model.counterClick += 1
          }
        }
        controller.model.timer = t
      })
      animationTimer.start
      content += menuBar
    }
    stage.setScene(scene)
  }
}