package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene, Cursor}
import scalafx.Includes._ //implicit
import scalafx.scene.control.MenuBar
import scala.collection.mutable.ListBuffer
import scalafx.scene.input.MouseEvent
import scalafx.animation._

class Countertop {
  val controller = new CountertopController()

  def counterTop(stage: PrimaryStage, menuBar: MenuBar): Unit = {
    val scene = new Scene {
      content = new ListBuffer()
      content += (controller.model.counterTopBackgroundImageView, controller.model.knifeImageView)

      onMouseMoved = (me: MouseEvent) => {
        controller.model.knifeImageView.toFront()
        controller.model.knifeImageView.x = me.x - 0.12 * controller.model.knife.width()
        controller.model.knifeImageView.y = me.y - 0.3 * controller.model.knife.height()
      }

      onMouseDragged = (me: MouseEvent) => {
        controller.model.knifeImageView.toFront()
        controller.model.knifeImageView.x = me.x - 0.12 * controller.model.knife.width()
        controller.model.knifeImageView.y = me.y - 0.3 * controller.model.knife.height()
      }

      var animationTimer: AnimationTimer = AnimationTimer(t => {
        if (controller.model.timer > 0) {
          val second = (t - controller.model.timer) / 1e9
          if (!content.contains(controller.model.startTextImageView) && controller.model.garlicTimer >= 0) {
            content += (controller.model.ingredients.garlicUncutImageView, controller.model.startTextImageView)
          }
          controller.model.garlicTimer -= second
          /*Garlic*/
          if (controller.model.garlicTimer <= 0) {
            if (content.contains(controller.model.startTextImageView)) {
              content -= controller.model.startTextImageView
              content += controller.model.clickToChopImageView
            }
            if (content.contains(controller.model.clickToChopImageView) && content.contains(controller.model.ingredients.garlicUncutImageView)) {
              controller.model.ingredients.garlicUncutImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= controller.model.ingredients.garlicUncutImageView
                content += (controller.model.ingredients.garlicCutImageView, controller.model.ingredients.garlicClove1ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.garlicCutImageView)) {
              controller.model.ingredients.garlicCutImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= (controller.model.ingredients.garlicCutImageView, controller.model.clickToChopImageView)
                content += (controller.model.ingredients.garlicClove2ImageView, controller.model.ingredients.garlicClove3ImageView, controller.model.holdDragImageView, controller.model.arrowImageView)
              }
            }
            if (content.contains(controller.model.ingredients.garlicClove2ImageView) && content.contains(controller.model.arrowImageView)) {
              controller.model.ingredients.garlicClove1ImageView.onMouseDragged = handle {
                onMouseReleased = (mer: MouseEvent) => {
                  controller.model.ingredients.garlicClove3ImageView.onMouseEntered = handle { //content only changes when the mouse is released within garlicClove3
                    if (!content.contains(controller.model.ingredients.mincedGarlic1ImageView)) {
                      content -= (controller.model.ingredients.garlicClove1ImageView, controller.model.ingredients.garlicClove2ImageView, controller.model.arrowImageView)
                      content += controller.model.ingredients.mincedGarlic1ImageView
                    }
                  }
                }
              }

            }
            if (content.contains(controller.model.ingredients.mincedGarlic1ImageView) && content.contains(controller.model.ingredients.garlicClove3ImageView) && !content.contains(controller.model.arrowImageView)) {
              controller.model.arrowImageView.setRotate(190)
              controller.model.arrowImageView.setX(520)
              controller.model.arrowImageView.setY(485)
              content += controller.model.arrowImageView
              controller.model.ingredients.garlicClove3ImageView.onMouseDragged = handle {
                onMouseReleased = (mer: MouseEvent) => {
                  controller.model.ingredients.mincedGarlic1ImageView.onMouseEntered = handle {
                    content -= (controller.model.ingredients.garlicClove3ImageView, controller.model.ingredients.mincedGarlic1ImageView, controller.model.arrowImageView, controller.model.holdDragImageView)
                    content += (controller.model.ingredients.mincedGarlicImageView, controller.model.garlicMincedTextImageView)
                  }
                }
              }
            }
          }
          if (content.contains(controller.model.garlicMincedTextImageView) && controller.model.onionTimer >= 0) {
            controller.model.onionTimer -= second
          }
          /*Onion*/
          if (controller.model.onionTimer <= 0) {
            if (content.contains(controller.model.garlicMincedTextImageView)) {
              content -= controller.model.garlicMincedTextImageView
              controller.model.ingredients.mincedGarlicImageView.setX(90)
              controller.model.ingredients.mincedGarlicImageView.setY(180)
              content += (controller.model.clickToChopImageView, controller.model.ingredients.onionUncutImageView)
            }
            if (content.contains(controller.model.ingredients.onionUncutImageView)) {
              controller.model.arrowImageView.setRotate(365)
              controller.model.ingredients.onionUncutImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= (controller.model.ingredients.onionUncutImageView, controller.model.clickToChopImageView)
                content += (controller.model.ingredients.onionCutImageView, controller.model.ingredients.onionCut1ImageView, controller.model.holdDragImageView, controller.model.arrowImageView)
              }
            }
            if (content.contains(controller.model.ingredients.onionCutImageView) && content.contains(controller.model.arrowImageView)) {
              controller.model.ingredients.onionCutImageView.onMouseDragged = handle {
                onMouseReleased = (mer: MouseEvent) => {
                  controller.model.ingredients.onionCut1ImageView.onMouseEntered = handle {
                    if (!content.contains(controller.model.ingredients.mincedOnion1ImageView)) {
                      content -= (controller.model.ingredients.onionCutImageView, controller.model.arrowImageView)
                      content += controller.model.ingredients.mincedOnion1ImageView
                    }
                  }
                }
              }
            }
            if (content.contains(controller.model.ingredients.mincedOnion1ImageView) && !content.contains(controller.model.arrowImageView)) {
              controller.model.arrowImageView.setRotate(190)
              controller.model.arrowImageView.setX(520)
              controller.model.arrowImageView.setY(485)
              content += controller.model.arrowImageView
              controller.model.ingredients.onionCut1ImageView.onMouseDragged = handle {
                onMouseReleased = (mer: MouseEvent) => {
                  controller.model.ingredients.mincedOnion1ImageView.onMouseEntered = handle {
                    if (!content.contains(controller.model.ingredients.mincedOnionImageView)) {
                      content -= (controller.model.ingredients.onionCut1ImageView, controller.model.ingredients.mincedOnion1ImageView, controller.model.holdDragImageView, controller.model.arrowImageView)
                      content += (controller.model.ingredients.mincedOnionImageView, controller.model.onionMincedTextImageView)
                    }
                  }
                }
              }
            }
          }
          if (content.contains(controller.model.onionMincedTextImageView) && controller.model.porkBellyTimer >= 0) {
            controller.model.porkBellyTimer -= second
          }
          /*Smoked pork belly*/
          if (controller.model.porkBellyTimer <= 0) {
            if (content.contains(controller.model.onionMincedTextImageView)) {
              content -= controller.model.onionMincedTextImageView
              controller.model.ingredients.mincedOnionImageView.setX(360)
              controller.model.ingredients.mincedOnionImageView.setY(180)
              content += (controller.model.clickToChopImageView, controller.model.ingredients.porkBellyUncutImageView)
            }
            if (content.contains(controller.model.clickToChopImageView) && content.contains(controller.model.ingredients.porkBellyUncutImageView) && controller.model.porkBellyCutTimer > 0) {
              controller.model.ingredients.porkBellyUncutImageView.onMousePressed = handle {
                controller.model.cuttingMeat.play()
                content -= (controller.model.ingredients.porkBellyUncutImageView, controller.model.clickToChopImageView)
                content += (controller.model.ingredients.porkBellyCutImageView, controller.model.ingredients.porkBellySliceImageView)
              }
            }
            if (content.contains(controller.model.ingredients.porkBellyCutImageView)) {
              controller.model.porkBellyCutTimer -= second
            }
            if (controller.model.porkBellyCutTimer <= 0) {
              content -= controller.model.ingredients.porkBellyCutImageView
              controller.model.ingredients.porkBellySliceImageView.setX(495)
              controller.model.ingredients.porkBellySliceImageView.setY(520)
            }
            if (content.contains(controller.model.ingredients.porkBellySliceImageView) && !content.contains(controller.model.clickToChopImageView) && controller.model.porkBellyCutTimer <= 0) {
              content += controller.model.clickToChopImageView
              controller.model.ingredients.porkBellySliceImageView.onMousePressed = handle {
                controller.model.cuttingMeat.play()
                content -= controller.model.ingredients.porkBellySliceImageView
                content += (controller.model.ingredients.porkBellyPiece1ImageView, controller.model.ingredients.porkBellySlice1ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.porkBellySlice1ImageView)) {
              controller.model.ingredients.porkBellySlice1ImageView.onMousePressed = handle {
                controller.model.cuttingMeat.play()
                content -= controller.model.ingredients.porkBellySlice1ImageView
                content += (controller.model.ingredients.porkBellyPiece2ImageView, controller.model.ingredients.porkBellySlice2ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.porkBellySlice2ImageView)) {
              controller.model.ingredients.porkBellySlice2ImageView.onMousePressed = handle {
                controller.model.cuttingMeat.play()
                content -= controller.model.ingredients.porkBellySlice2ImageView
                content += (controller.model.ingredients.porkBellyPiece3ImageView, controller.model.ingredients.porkBellySlice3ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.porkBellySlice3ImageView)) {
              controller.model.ingredients.porkBellySlice3ImageView.onMousePressed = handle {
                controller.model.cuttingMeat.play()
                content -= (controller.model.ingredients.porkBellySlice3ImageView, controller.model.clickToChopImageView)
                content += (controller.model.ingredients.porkBellyPiece4ImageView, controller.model.ingredients.porkBellyPiece5ImageView, controller.model.porkBellyTextImageView)
              }
            }
          }
          if (content.contains(controller.model.porkBellyTextImageView) && controller.model.mushroomTimer >= 0) {
            controller.model.mushroomTimer -= second
          }
          /*Mushroom*/
          if (controller.model.mushroomTimer <= 0) {
            if (content.contains(controller.model.porkBellyTextImageView)) {
              content -= controller.model.porkBellyTextImageView
              controller.model.ingredients.porkBellyPiece1ImageView.setX(680)
              controller.model.ingredients.porkBellyPiece1ImageView.setY(200)
              controller.model.ingredients.porkBellyPiece2ImageView.setX(665)
              controller.model.ingredients.porkBellyPiece2ImageView.setY(215)
              controller.model.ingredients.porkBellyPiece3ImageView.setX(650)
              controller.model.ingredients.porkBellyPiece3ImageView.setY(230)
              controller.model.ingredients.porkBellyPiece4ImageView.setX(635)
              controller.model.ingredients.porkBellyPiece4ImageView.setY(245)
              controller.model.ingredients.porkBellyPiece5ImageView.setX(620)
              controller.model.ingredients.porkBellyPiece5ImageView.setY(260)
              content += (controller.model.clickToChopImageView, controller.model.ingredients.mushroomUncutImageView)
            }
            if (content.contains(controller.model.clickToChopImageView) && content.contains(controller.model.ingredients.mushroomUncutImageView)) {
              controller.model.ingredients.mushroomUncutImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= controller.model.ingredients.mushroomUncutImageView
                content += (controller.model.ingredients.mushroomCut1ImageView, controller.model.ingredients.mushroomPiece1ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.mushroomCut1ImageView)) {
              controller.model.ingredients.mushroomCut1ImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= controller.model.ingredients.mushroomCut1ImageView
                content += (controller.model.ingredients.mushroomCut2ImageView, controller.model.ingredients.mushroomPiece2ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.mushroomCut2ImageView)) {
              controller.model.ingredients.mushroomCut2ImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= controller.model.ingredients.mushroomCut2ImageView
                content += (controller.model.ingredients.mushroomCut3ImageView, controller.model.ingredients.mushroomPiece3ImageView)
              }
            }
            if (content.contains(controller.model.ingredients.mushroomCut3ImageView)) {
              controller.model.ingredients.mushroomCut3ImageView.onMousePressed = handle {
                controller.model.knifeChop.play()
                content -= (controller.model.ingredients.mushroomCut3ImageView, controller.model.clickToChopImageView)
                content += (controller.model.ingredients.mushroomPiece4ImageView, controller.model.ingredients.mushroomPiece5ImageView, controller.model.mushroomTextImageView)
              }
            }
          }
          if (content.contains(controller.model.mushroomTextImageView) && controller.model.allPrepTimer >= 0) {
            controller.model.allPrepTimer -= second
          }
          if (controller.model.allPrepTimer <= 0) {
            if (content.contains(controller.model.mushroomTextImageView)) {
              content -= controller.model.mushroomTextImageView
              controller.model.ingredients.mushroomPiece1ImageView.setX(980)
              controller.model.ingredients.mushroomPiece1ImageView.setY(200)
              controller.model.ingredients.mushroomPiece2ImageView.setX(955)
              controller.model.ingredients.mushroomPiece2ImageView.setY(205)
              controller.model.ingredients.mushroomPiece3ImageView.setX(930)
              controller.model.ingredients.mushroomPiece3ImageView.setY(210)
              controller.model.ingredients.mushroomPiece4ImageView.setX(905)
              controller.model.ingredients.mushroomPiece4ImageView.setY(215)
              controller.model.ingredients.mushroomPiece5ImageView.setX(870)
              controller.model.ingredients.mushroomPiece5ImageView.setY(220)
              content += controller.model.allPrepImageView
            }
          }
          if (content.contains(controller.model.allPrepImageView) && controller.model.nextSceneTimer >= 0) {
            controller.model.nextSceneTimer -= second
          }
          if (controller.model.nextSceneTimer <= 0) {
            animationTimer.stop
            MainApp.sceneCategoryCounter += 1
            MainApp.sceneCategory()
          }
        }
        controller.model.timer = t
      })
      animationTimer.start
      content += menuBar
    }
    stage.setScene(scene)
    scene.setCursor(Cursor.ClosedHand)
  }
}