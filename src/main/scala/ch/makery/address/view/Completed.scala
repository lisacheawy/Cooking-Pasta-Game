package ch.makery.address.view

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._ //implicit
import scalafx.scene.control.MenuBar
import scala.collection.mutable.ListBuffer
import scalafx.animation._

class Completed {
  val controller = new CompletedController()

  def completed(stage: PrimaryStage, menuBar: MenuBar): Unit = {
    val scene = new Scene {
      content = new ListBuffer()
      content += (controller.model.completedBackgroundImageView, controller.model.carbonaraImageView)

      var animationTimer: AnimationTimer = AnimationTimer(t => {
        if (controller.model.timer > 0) {
          val second = (t - controller.model.timer) / 1e9
          /*Alternating between images to create smoke movement*/
          if (controller.model.smokeTimer >= 0.5 && controller.model.isSwap) {
            controller.model.isSwap = false
          }
          else if (controller.model.smokeTimer < 0.5 && controller.model.isSwap) {
            controller.model.smokeTimer += second
          }
          else if (controller.model.smokeTimer >= 0 && !controller.model.isSwap) {
            if (content.contains(controller.model.smoke2ImageView)) {
              content -= controller.model.smoke2ImageView
            }
            if (!content.contains(controller.model.smoke1ImageView)) {
              content += controller.model.smoke1ImageView
            }
            controller.model.smokeTimer -= second
          }
          else if (controller.model.smokeTimer <= 0) {
            if (content.contains(controller.model.smoke1ImageView)) {
              content -= controller.model.smoke1ImageView
              content += controller.model.smoke2ImageView
              controller.model.isSwap = true
            }
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