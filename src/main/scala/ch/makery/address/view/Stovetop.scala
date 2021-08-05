package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene, Cursor}
import scalafx.Includes._ //implicit
import scalafx.scene.control.MenuBar
import scala.collection.mutable.ListBuffer
import scalafx.scene.image.ImageView
import scalafx.scene.input.{MouseEvent, KeyEvent, KeyCode}
import scalafx.animation._


class Stovetop {
  val controller = new StovetopController()
  var scene = new Scene

  def stoveTop(stage: PrimaryStage, menuBar: MenuBar): Unit = {
    scene = new Scene {
      content = new ListBuffer()
      content += (controller.model.stoveBackgroundImageView, controller.model.pointer1ImageView, controller.model.pointer2ImageView)

      var animationTimer: AnimationTimer = AnimationTimer(t => {
        if (controller.model.timer.timer > 0) {
          val second = (t - controller.model.timer.timer) / 1e9
          if (!content.contains(controller.model.instructions.startCookingImageView) && controller.model.timer.startTimer >= 0) {
            content += (controller.model.instructions.startCookingImageView, controller.model.potImageView, controller.model.panImageView, controller.model.calmWaterImageView)
          }
          controller.model.timer.startTimer -= second
          if (controller.model.timer.startTimer <= 0) {
            if (content.contains(controller.model.instructions.startCookingImageView)) {
              content -= controller.model.instructions.startCookingImageView
              content += controller.model.instructions.setFire1ImageView
            }
            if (content.contains(controller.model.instructions.setFire1ImageView) && controller.model.pointer1ImageView.getRotate() != -45) {
              rotatePointer(controller.model.pointer1ImageView, -45)
            }
            /*First stove actions*/
            if (controller.model.pointer1ImageView.getRotate() == -45) {
              controller.model.timer.boilTimer += second
              if (content.contains(controller.model.instructions.setFire1ImageView)) {
                content -= controller.model.instructions.setFire1ImageView
              }
              /*Alternating between images to create fire movement (continuous loop between 1. to 4.)*/
              //4. when timer.timer is >= 1 and control.isSwap is true, set it to false. hence, it goes back to 1
              if (controller.model.timer.fire1Timer >= 0.5 && controller.model.control.isSwap) {
                controller.model.control.isSwap = false
              }
              //3. when timer.timer is less than 1, increment it
              else if (controller.model.timer.fire1Timer < 0.5 && controller.model.control.isSwap) {
                controller.model.timer.fire1Timer += second
              }
              //1. when fire timer.timer is >=0 AND control.isSwap is false, decrement the timer.timer
              else if (controller.model.timer.fire1Timer >= 0 && !controller.model.control.isSwap) {
                if (content.contains(controller.model.fire2ImageView)) { //remove fire2 if present
                  content -= controller.model.fire2ImageView
                }
                if (!content.contains(controller.model.fire1ImageView)) { //add fire1 if not present
                  content += controller.model.fire1ImageView
                }
                controller.model.timer.fire1Timer -= second //decrement the timer.timer
              }
              //2. when timer.timer is <=0, replace fire1 with fire2 and set control.isSwap to true
              else if (controller.model.timer.fire1Timer <= 0) {
                if (content.contains(controller.model.fire1ImageView)) {
                  content -= controller.model.fire1ImageView
                  content += controller.model.fire2ImageView
                  controller.model.control.isSwap = true
                }
              }
              if (controller.model.timer.boilTextTimer >= 0) {
                controller.model.timer.boilTextTimer -= second
                if (!content.contains(controller.model.instructions.letBoilImageView)) {
                  content += controller.model.instructions.letBoilImageView
                }
              }
              if (controller.model.timer.boilTextTimer <= 0 && content.contains(controller.model.instructions.letBoilImageView)) {
                content -= controller.model.instructions.letBoilImageView
              }
              if (controller.model.timer.boilTimer >= 5 && !controller.model.control.isBoiled) {
                if (content.contains(controller.model.calmWaterImageView)) {
                  content -= controller.model.calmWaterImageView
                }
                if (!controller.model.control.boilAudioStart){
                  controller.model.boilingWater.play()
                  controller.model.control.boilAudioStart = true
                }
                /*Alternating between images to create water movement (continuous loop between 1. to 4.)*/
                //4. when timer.timer is >= 1 and controller.model.control.isSwap is true, set it to false. hence, it goes back to 1
                if (controller.model.timer.waterTimer >= 0.5 && controller.model.control.isSwap2) {
                  controller.model.control.isSwap2 = false
                }
                //3. when timer.timer is less than 1, increment it
                else if (controller.model.timer.waterTimer < 0.5 && controller.model.control.isSwap2 && !controller.model.control.isBoiled) {
                  controller.model.timer.waterTimer += second
                }
                //1. when water timer.timer is >=0 AND controller.model.control.isSwap is false, decrement the timer.timer
                else if (controller.model.timer.waterTimer >= 0 && !controller.model.control.isSwap2 && !controller.model.control.isBoiled) {
                  if (content.contains(controller.model.boilingWater2ImageView)) { //remove boilingwater2 if present
                    content -= controller.model.boilingWater2ImageView
                  }
                  if (!content.contains(controller.model.boilingWater1ImageView)) { //add boilingwater1 if not present
                    content += controller.model.boilingWater1ImageView
                  }
                  controller.model.timer.waterTimer -= second //decrement the timer.timer
                }
                //2. when timer.timer is <=0, replace boilingwater1 with boilingwater2 and set control.isSwap to true
                else if (controller.model.timer.waterTimer <= 0) {
                  if (content.contains(controller.model.boilingWater1ImageView)) {
                    content -= controller.model.boilingWater1ImageView
                    content += controller.model.boilingWater2ImageView
                    controller.model.control.isSwap2 = true
                  }
                }
              }
              if (controller.model.timer.boilTimer >= 7) {
                if (!content.contains(controller.model.instructions.addPastaImageView) && !controller.model.control.isBoiled && !controller.model.control.spegetisBoiling) {
                  content += controller.model.instructions.addPastaImageView
                  onKeyPressed = (ke: KeyEvent) => {
                    if (ke.code == KeyCode.Space && !content.contains(controller.model.ingredients.boilingSpegetImageView) && !content.contains(controller.model.instructions.setFire2ImageView)) {
                      controller.model.control.isBoiled = true
                      controller.model.control.spegetisBoiling = true
                      content -= (controller.model.instructions.addPastaImageView, controller.model.boilingWater1ImageView, controller.model.boilingWater2ImageView)
                      content += (controller.model.ingredients.boilingSpegetImageView, controller.model.instructions.setFire2ImageView)
                    }
                  }
                }
              }
              if (controller.model.control.spegetisBoiling) {
                controller.model.timer.spegetBoilTimer += second
              }
              if (content.contains(controller.model.instructions.setFire2ImageView) && controller.model.pointer2ImageView.getRotate() != 45) {
                rotatePointer(controller.model.pointer2ImageView, 45)
              }
              /*Changing pasta cooking picture*/
              if (controller.model.control.firstCookDone || controller.model.timer.spegetBoilTimer >= 7) {
                if (!content.contains(controller.model.ingredients.boilingSpeget1ImageView) && content.contains(controller.model.ingredients.boilingSpegetImageView)) {
                  content -= controller.model.ingredients.boilingSpegetImageView
                  content += controller.model.ingredients.boilingSpeget1ImageView
                }
              }
              if (controller.model.control.thirdCookDone || controller.model.timer.spegetBoilTimer >= 20) {
                if (!content.contains(controller.model.ingredients.boiledSpegetImageView) && content.contains(controller.model.ingredients.boilingSpeget1ImageView)) {
                  content -= controller.model.ingredients.boilingSpeget1ImageView
                  content += controller.model.ingredients.boiledSpegetImageView
                }
              }
            }
            /*Second stove actions*/
            if (controller.model.pointer2ImageView.getRotate() == 45) {
              if (content.contains(controller.model.instructions.setFire2ImageView) && !content.contains(controller.model.ingredients.oliveoilPourImageView)) {
                content -= controller.model.instructions.setFire2ImageView
                content += (controller.model.instructions.addOliveOilImageView)
              }
              //Alternating between images to create fire movement
              if (controller.model.timer.fire2Timer >= 0.5 && controller.model.control.isSwap1) {
                controller.model.control.isSwap1 = false
              }
              else if (controller.model.timer.fire2Timer < 0.5 && controller.model.control.isSwap1) {
                controller.model.timer.fire2Timer += second
              }
              else if (controller.model.timer.fire2Timer >= 0 && !controller.model.control.isSwap1) {
                if (content.contains(controller.model.fire4ImageView)) {
                  content -= controller.model.fire4ImageView
                }
                if (!content.contains(controller.model.fire3ImageView)) {
                  content += controller.model.fire3ImageView
                }
                controller.model.timer.fire2Timer -= second
              }
              else if (controller.model.timer.fire2Timer <= 0) {
                if (content.contains(controller.model.fire3ImageView)) {
                  content -= controller.model.fire3ImageView
                  content += controller.model.fire4ImageView
                  controller.model.control.isSwap1 = true
                }
              }
              if (!controller.model.control.firstCookDone) {
                /*Olive oil*/
                if (content.contains(controller.model.instructions.addOliveOilImageView)) {
                  scene.setCursor(Cursor.ClosedHand)
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.oliveoilPourImageView) && !controller.model.ingredients.hasOliveOil) {
                      content += controller.model.ingredients.oliveoilPourImageView
                    }
                    controller.model.ingredients.oliveoilPourImageView.toFront()
                    controller.model.ingredients.oliveoilPourImageView.x = me.x - 0.3 * controller.model.ingredients.oliveoilPour.width()
                    controller.model.ingredients.oliveoilPourImageView.y = me.y - 0.5 * controller.model.ingredients.oliveoilPour.height()
                  }
                  controller.model.panImageView.onMouseEntered = handle {
                    onMousePressed = handle {
                      if (content.contains(controller.model.instructions.addOliveOilImageView) && content.contains(controller.model.ingredients.oliveoilPourImageView) && !content.contains(controller.model.ingredients.oilBaseImageView) && !content.contains(controller.model.instructions.addMincedImageView))
                        content += (controller.model.ingredients.oilBaseImageView, controller.model.instructions.addMincedImageView)
                      content -= (controller.model.instructions.addOliveOilImageView, controller.model.ingredients.oliveoilPourImageView)
                      controller.model.control.cookStart = true
                      controller.model.ingredients.hasOliveOil = true
                    }
                  }
                }
                /*Minced items*/
                if (content.contains(controller.model.instructions.addMincedImageView)) {
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.mincedItemsImageView) && !controller.model.ingredients.hasMincedItems) {
                      content += controller.model.ingredients.mincedItemsImageView
                    }
                    controller.model.ingredients.mincedItemsImageView.toFront()
                    controller.model.ingredients.mincedItemsImageView.x = me.x - 0.5 * controller.model.ingredients.mincedItems.width()
                    controller.model.ingredients.mincedItemsImageView.y = me.y - 1.05 * controller.model.ingredients.mincedItems.height()
                  }
                  controller.model.ingredients.oilBaseImageView.onMousePressed = handle {
                    controller.model.sizzling.play()
                    if (content.contains(controller.model.instructions.addMincedImageView) && content.contains(controller.model.ingredients.mincedItemsImageView) && !content.contains(controller.model.ingredients.mincedonPanImageView) && !content.contains(controller.model.instructions.addPorkImageView)) {
                      content += (controller.model.ingredients.mincedonPanImageView, controller.model.instructions.addPorkImageView)
                      content -= (controller.model.instructions.addMincedImageView, controller.model.ingredients.mincedItemsImageView)
                      controller.model.ingredients.oilBaseImageView.toFront()
                      controller.model.ingredients.hasMincedItems = true
                    }
                  }
                }
                /*Smoked pork belly*/
                if (content.contains(controller.model.instructions.addPorkImageView)) {
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.smokedPorkImageView) && !controller.model.ingredients.hasPorkBelly) {
                      content += controller.model.ingredients.smokedPorkImageView
                    }
                    controller.model.ingredients.smokedPorkImageView.toFront()
                    controller.model.ingredients.smokedPorkImageView.x = me.x - 0.5 * controller.model.ingredients.smokedPork.width()
                    controller.model.ingredients.smokedPorkImageView.y = me.y - 1.05 * controller.model.ingredients.smokedPork.height()
                  }
                  controller.model.ingredients.oilBaseImageView.onMousePressed = handle {
                    if (content.contains(controller.model.instructions.addPorkImageView) && content.contains(controller.model.ingredients.smokedPorkImageView) && !content.contains(controller.model.ingredients.porkonPanImageView) && !content.contains(controller.model.instructions.shakePanImageView)) {
                      content += (controller.model.ingredients.porkonPanImageView, controller.model.instructions.shakePanImageView)
                      content -= (controller.model.instructions.addPorkImageView, controller.model.ingredients.smokedPorkImageView)
                      controller.model.ingredients.hasPorkBelly = true
                    }
                  }
                }
                if (content.contains(controller.model.instructions.shakePanImageView)) {
                  scene.setCursor(Cursor.Default)
                  if (controller.model.control.panCounter < 2) {
                    onKeyPressed = (ke: KeyEvent) => {
                      if (ke.code == KeyCode.Up && !controller.model.control.movePan && controller.model.control.moveCounter == 30) { //1. Up key is pressed
                        controller.model.panMove.play()
                        controller.model.control.movePan = true
                        controller.model.control.shakeCounter = 0
                      }
                    }
                    shakePan(controller.model.ingredients.oilBaseImageView, controller.model.ingredients.mincedonPanImageView, controller.model.ingredients.porkonPanImageView)
                  }
                }
              }
              if (controller.model.control.panCounter == 2) {
                content -= (controller.model.instructions.shakePanImageView, controller.model.ingredients.oilBaseImageView, controller.model.ingredients.mincedonPanImageView, controller.model.ingredients.porkonPanImageView)
                controller.model.control.firstCookDone = true
                controller.model.control.panCounter = 0
              }
              if (controller.model.control.firstCookDone && !controller.model.control.secondCookDone) {
                if (!content.contains(controller.model.ingredients.cookedBaseImageView) && !content.contains(controller.model.instructions.addMushroomsImageView)) {
                  content += (controller.model.ingredients.cookedBaseImageView, controller.model.instructions.addMushroomsImageView)
                }
                /*Mushrooms*/
                if (content.contains(controller.model.instructions.addMushroomsImageView)) {
                  scene.setCursor(Cursor.ClosedHand)
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.slicedMushroomsImageView) && !controller.model.ingredients.hasMushrooms) {
                      content += controller.model.ingredients.slicedMushroomsImageView
                    }
                    controller.model.ingredients.slicedMushroomsImageView.toFront()
                    controller.model.ingredients.slicedMushroomsImageView.x = me.x - 0.5 * controller.model.ingredients.slicedMushrooms.width()
                    controller.model.ingredients.slicedMushroomsImageView.y = me.y - 1.05 * controller.model.ingredients.slicedMushrooms.height()
                  }
                  controller.model.ingredients.cookedBaseImageView.onMousePressed = handle {
                    if (content.contains(controller.model.instructions.addMushroomsImageView) && content.contains(controller.model.ingredients.slicedMushroomsImageView) && !content.contains(controller.model.ingredients.mushroomonPanImageView) && !content.contains(controller.model.instructions.addWhippingCreamImageView)) {
                      content += (controller.model.ingredients.mushroomonPanImageView, controller.model.instructions.addWhippingCreamImageView)
                      content -= (controller.model.instructions.addMushroomsImageView, controller.model.ingredients.slicedMushroomsImageView)
                      controller.model.ingredients.cookedBaseImageView.toFront()
                      controller.model.ingredients.hasMushrooms = true
                    }
                  }
                }
                /*Whipping Cream*/
                if (content.contains(controller.model.instructions.addWhippingCreamImageView)) {
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.whippingcreamPourImageView) && !controller.model.ingredients.hasWhippingCream) {
                      content += controller.model.ingredients.whippingcreamPourImageView
                    }
                    controller.model.ingredients.whippingcreamPourImageView.toFront()
                    controller.model.ingredients.whippingcreamPourImageView.x = me.x - 0.3 * controller.model.ingredients.whippingcreamPour.width()
                    controller.model.ingredients.whippingcreamPourImageView.y = me.y - 0.75 * controller.model.ingredients.whippingcreamPour.height()
                  }
                  controller.model.ingredients.cookedBaseImageView.onMousePressed = handle {
                    controller.model.pouring.play()
                    if (content.contains(controller.model.instructions.addWhippingCreamImageView) && content.contains(controller.model.ingredients.whippingcreamPourImageView) && !content.contains(controller.model.ingredients.creamBaseImageView) && !content.contains(controller.model.instructions.shakePanImageView)) {
                      content += (controller.model.ingredients.creamBaseImageView, controller.model.instructions.shakePanImageView)
                      content -= (controller.model.instructions.addWhippingCreamImageView, controller.model.ingredients.whippingcreamPourImageView)
                      controller.model.ingredients.hasWhippingCream = true
                    }
                  }
                }
                if (content.contains(controller.model.instructions.shakePanImageView)) {
                  scene.setCursor(Cursor.Default)
                  if (controller.model.control.panCounter < 2) {
                    onKeyPressed = (ke: KeyEvent) => {
                      if (ke.code == KeyCode.Up && !controller.model.control.movePan && controller.model.control.moveCounter == 30) { // Up key is pressed
                        controller.model.panMove.play()
                        controller.model.control.movePan = true
                        controller.model.control.shakeCounter = 0
                      }
                    }
                    shakePan(controller.model.ingredients.cookedBaseImageView, controller.model.ingredients.mushroomonPanImageView, controller.model.ingredients.creamBaseImageView)
                  }
                }
              }
              if (controller.model.control.panCounter == 2) {
                content -= (controller.model.instructions.shakePanImageView, controller.model.ingredients.cookedBaseImageView, controller.model.ingredients.mushroomonPanImageView, controller.model.ingredients.creamBaseImageView)
                controller.model.control.secondCookDone = true
                controller.model.control.panCounter = 0
              }
              if (controller.model.control.secondCookDone && !controller.model.control.thirdCookDone) {
                if (!content.contains(controller.model.ingredients.cookedBase1ImageView) && !content.contains(controller.model.instructions.addSaltImageView)) {
                  content += (controller.model.ingredients.cookedBase1ImageView, controller.model.instructions.addSaltImageView)
                }
                /*Salt*/
                if (content.contains(controller.model.instructions.addSaltImageView)) {
                  scene.setCursor(Cursor.ClosedHand)
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.saltPourImageView) && !controller.model.ingredients.hasSalt) {
                      content += controller.model.ingredients.saltPourImageView
                    }
                    controller.model.ingredients.saltPourImageView.toFront()
                    controller.model.ingredients.saltPourImageView.x = me.x - 0.5 * controller.model.ingredients.saltPour.width()
                    controller.model.ingredients.saltPourImageView.y = me.y - 0.72 * controller.model.ingredients.saltPour.height()
                  }
                  controller.model.ingredients.cookedBase1ImageView.onMousePressed = handle {
                    controller.model.sprinkle.play()
                    if (content.contains(controller.model.ingredients.saltPourImageView) && content.contains(controller.model.instructions.addSaltImageView) && !content.contains(controller.model.instructions.addPepperImageView)) {
                      content += controller.model.instructions.addPepperImageView
                      content -= (controller.model.instructions.addSaltImageView, controller.model.ingredients.saltPourImageView)
                      controller.model.ingredients.cookedBaseImageView.toFront()
                      controller.model.ingredients.hasSalt = true
                    }
                  }
                }
                /*Pepper*/
                if (content.contains(controller.model.instructions.addPepperImageView)) {
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.pepperPourImageView) && !controller.model.ingredients.hasPepper) {
                      content += controller.model.ingredients.pepperPourImageView
                    }
                    controller.model.ingredients.pepperPourImageView.toFront()
                    controller.model.ingredients.pepperPourImageView.x = me.x - 0.5 * controller.model.ingredients.pepperPour.width()
                    controller.model.ingredients.pepperPourImageView.y = me.y - 0.72 * controller.model.ingredients.pepperPour.height()
                  }
                  controller.model.ingredients.cookedBase1ImageView.onMousePressed = handle {
                    controller.model.sprinkle.play()
                    if (content.contains(controller.model.instructions.addPepperImageView) && content.contains(controller.model.ingredients.pepperPourImageView) && !content.contains(controller.model.ingredients.pepperonPanImageView) && !content.contains(controller.model.instructions.addParsleyImageView)) {
                      content += (controller.model.instructions.addParsleyImageView, controller.model.ingredients.pepperonPanImageView)
                      content -= (controller.model.instructions.addPepperImageView, controller.model.ingredients.pepperPourImageView)
                      controller.model.ingredients.hasPepper = true
                    }
                  }
                }
                /*Parsley*/
                if (content.contains(controller.model.instructions.addParsleyImageView)) {
                  onMouseMoved = (me: MouseEvent) => {
                    if (!content.contains(controller.model.ingredients.parsleyPourImageView) && !controller.model.ingredients.hasParsley) {
                      content += controller.model.ingredients.parsleyPourImageView
                    }
                    controller.model.ingredients.parsleyPourImageView.toFront()
                    controller.model.ingredients.parsleyPourImageView.x = me.x - 0.5 * controller.model.ingredients.parsleyPour.width()
                    controller.model.ingredients.parsleyPourImageView.y = me.y - 1.05 * controller.model.ingredients.parsleyPour.height()
                  }
                  controller.model.ingredients.cookedBase1ImageView.onMousePressed = handle {
                    controller.model.sprinkle.play()
                    if (content.contains(controller.model.instructions.addParsleyImageView) && content.contains(controller.model.ingredients.parsleyPourImageView) && !content.contains(controller.model.ingredients.parsleyonPanImageView) && !content.contains(controller.model.instructions.shakePanImageView)) {
                      content += (controller.model.ingredients.parsleyonPanImageView, controller.model.instructions.shakePanImageView)
                      content -= (controller.model.instructions.addParsleyImageView, controller.model.ingredients.parsleyPourImageView)
                      controller.model.ingredients.hasParsley = true
                    }
                  }
                }
                if (content.contains(controller.model.instructions.shakePanImageView)) {
                  scene.setCursor(Cursor.Default)
                  if (controller.model.control.panCounter < 2) {
                    onKeyPressed = (ke: KeyEvent) => {
                      if (ke.code == KeyCode.Up && !controller.model.control.movePan && controller.model.control.moveCounter == 30) { // Up key is pressed
                        controller.model.panMove.play()
                        controller.model.control.movePan = true
                        controller.model.control.shakeCounter = 0
                      }
                    }
                    shakePan(controller.model.ingredients.cookedBase1ImageView, controller.model.ingredients.pepperonPanImageView, controller.model.ingredients.parsleyonPanImageView)
                  }
                }
              }
              if (controller.model.control.panCounter == 2) {
                content -= (controller.model.instructions.shakePanImageView, controller.model.ingredients.cookedBase1ImageView, controller.model.ingredients.pepperonPanImageView, controller.model.ingredients.parsleyonPanImageView)
                controller.model.control.thirdCookDone = true
                controller.model.control.panCounter = 0
              }
              if (controller.model.control.thirdCookDone) {
                if (!content.contains(controller.model.ingredients.cookedBase2ImageView) && !content.contains(controller.model.instructions.addPastaPanImageView)) {
                  content += (controller.model.ingredients.cookedBase2ImageView, controller.model.instructions.addPastaPanImageView)
                }
                if (content.contains(controller.model.instructions.addPastaPanImageView)) {
                  onKeyPressed = (ke: KeyEvent) => {
                    if (ke.code == KeyCode.Space && !content.contains(controller.model.ingredients.pastaonPanImageView) && !content.contains(controller.model.instructions.grateParmesanImageView)) {
                      controller.model.control.isBoiled = false
                      content -= (controller.model.instructions.addPastaPanImageView, controller.model.ingredients.boiledSpegetImageView)
                      content += (controller.model.ingredients.pastaonPanImageView, controller.model.instructions.grateParmesanImageView)
                    }
                  }
                }
                if (content.contains(controller.model.instructions.grateParmesanImageView)) {
                  if (!content.contains(controller.model.graterImageView)) {
                    content += controller.model.graterImageView
                  }
                  if (!content.contains(controller.model.ingredients.parmesanBlock1ImageView) && !content.contains(controller.model.ingredients.shreddedParmesan1ImageView)) {
                    content += controller.model.ingredients.parmesanBlock1ImageView
                  }
                  if (controller.model.control.parmesanCounter < 4) {
                    onKeyPressed = (ke: KeyEvent) => {
                      if (ke.code == KeyCode.Left && !controller.model.control.moveParmesan && controller.model.control.dragCounter == 70) {
                        controller.model.grate.play()
                        controller.model.control.moveParmesan = true
                        controller.model.control.grateCounter = 0
                      }
                    }
                    if (content.contains(controller.model.ingredients.parmesanBlock1ImageView)) {
                    grateParmesan(controller.model.ingredients.parmesanBlock1ImageView)
                    }
                    else if (content.contains(controller.model.ingredients.parmesanBlock2ImageView)) {
                      grateParmesan(controller.model.ingredients.parmesanBlock2ImageView)
                    }
                    else if (content.contains(controller.model.ingredients.parmesanBlock3ImageView)) {
                      grateParmesan(controller.model.ingredients.parmesanBlock3ImageView)
                    }
                  }
                  if (controller.model.control.parmesanCounter == 1) {
                    if (!content.contains(controller.model.ingredients.shreddedParmesan1ImageView)) {
                      content += (controller.model.ingredients.shreddedParmesan1ImageView, controller.model.ingredients.parmesanBlock2ImageView)
                      content -= controller.model.ingredients.parmesanBlock1ImageView
                    }
                  }
                  if (controller.model.control.parmesanCounter == 2) {
                    if (!content.contains(controller.model.ingredients.shreddedParmesan2ImageView)) {
                      content += (controller.model.ingredients.shreddedParmesan2ImageView, controller.model.ingredients.parmesanBlock3ImageView)
                      content -= controller.model.ingredients.parmesanBlock2ImageView
                    }
                  }
                  if (controller.model.control.parmesanCounter == 3) {
                    if (!content.contains(controller.model.ingredients.shreddedParmesan3ImageView)) {
                      content += (controller.model.ingredients.shreddedParmesan3ImageView, controller.model.instructions.doneImageView)
                      content -= (controller.model.ingredients.parmesanBlock3ImageView, controller.model.graterImageView, controller.model.instructions.grateParmesanImageView)
                    }
                  }
                }
              }
              /*Alternating between images to create smoke movement*/
              if (controller.model.control.cookStart) {
                if (controller.model.timer.smokeTimer >= 0.5 && controller.model.control.isSwap3) {
                  controller.model.control.isSwap3 = false
                }
                else if (controller.model.timer.smokeTimer < 0.5 && controller.model.control.isSwap3) {
                  controller.model.timer.smokeTimer += second
                }
                else if (controller.model.timer.smokeTimer >= 0 && !controller.model.control.isSwap3) {
                  if (content.contains(controller.model.smoke2ImageView)) {
                    content -= controller.model.smoke2ImageView
                  }
                  if (!content.contains(controller.model.smoke1ImageView)) {
                    content += controller.model.smoke1ImageView
                  }
                  controller.model.timer.smokeTimer -= second
                }
                else if (controller.model.timer.smokeTimer <= 0) {
                  if (content.contains(controller.model.smoke1ImageView)) {
                    content -= controller.model.smoke1ImageView
                    content += controller.model.smoke2ImageView
                    controller.model.control.isSwap3 = true
                  }
                }
              }
            }
          }
          if (content.contains(controller.model.instructions.doneImageView) && controller.model.timer.doneTimer >= 0) {
            controller.model.timer.doneTimer -= second
          }
          if (controller.model.timer.doneTimer <= 0) {
            controller.model.boilingWater.stop()
            controller.model.sizzling.stop()
            animationTimer.stop
            MainApp.sceneCategoryCounter += 1
            MainApp.sceneCategory()
          }
        }
        controller.model.timer.timer = t
      })
      animationTimer.start
      content += menuBar
    }
    stage.setScene(scene)
  }

  def shakePan(image1: ImageView, image2: ImageView, image3: ImageView): Unit = {
    if (controller.model.control.shakeCounter < 1) {
      if (controller.model.control.moveCounter < 0 && controller.model.control.isDone) {
        controller.model.control.isDone = false
        controller.model.control.moveCounter = 30
        controller.model.control.shakeCounter += 1
        controller.model.control.panCounter += 1
      }
      else if (controller.model.control.movePan && controller.model.control.moveCounter >= 0) { //2. moves the pan up and decrements the control.moveCounter
        image1.y = image1.y.value - 15 * controller.model.timer.interval
        image2.y = image2.y.value - 10 * controller.model.timer.interval
        image3.y = image3.y.value - 10 * controller.model.timer.interval
        controller.model.panImageView.y = controller.model.panImageView.y.value - 30 * controller.model.timer.interval
        controller.model.control.moveCounter -= 1
      }
      else if (controller.model.control.moveCounter < 0) { //3. resets the control.moveCounter to 10 when the pan has finished moving up
        controller.model.control.movePan = false
        controller.model.control.moveCounter = 30
        controller.model.control.isDone = true //indicates whether the pan is done moving up
      }
      else if (controller.model.control.isDone && controller.model.control.moveCounter >= 0) { //4. moves the pan back down
        image1.y = image1.y.value + 15 * controller.model.timer.interval
        image2.y = image2.y.value + 10 * controller.model.timer.interval
        image3.y = image3.y.value + 10 * controller.model.timer.interval
        controller.model.panImageView.y = controller.model.panImageView.y.value + 30 * controller.model.timer.interval
        controller.model.control.moveCounter -= 1
      }
    }
  }

  def grateParmesan(image: ImageView): Unit = {
    if (controller.model.control.grateCounter < 1) {
      if (controller.model.control.dragCounter < 0 && controller.model.control.isDone) {
        controller.model.control.isDone = false
        controller.model.control.dragCounter = 70
        controller.model.control.grateCounter += 1
        controller.model.control.parmesanCounter += 1
      }
      else if (controller.model.control.moveParmesan && controller.model.control.dragCounter >= 0) {
        image.x = image.x.value - 50 * controller.model.timer.interval
        controller.model.control.dragCounter -= 1
      }
      else if (controller.model.control.dragCounter < 0) {
        controller.model.control.moveParmesan = false
        controller.model.control.dragCounter = 70
        controller.model.control.isDone = true
      }
      else if (controller.model.control.isDone && controller.model.control.dragCounter >= 0) {
        image.x = image.x.value + 50 * controller.model.timer.interval
        controller.model.control.dragCounter -= 1
      }
    }
  }

  def rotatePointer(pointer: ImageView, degree: Int): Unit = {
    pointer.onMousePressed = (me: MouseEvent) => {
      controller.model.stove.play()
      var sceneX = me.x
      pointer.onMouseDragged = (me: MouseEvent) => {
        var angle = me.x - sceneX
        if (angle > 45) {
          angle = 45
        }
        if (angle < -45) {
          angle = -45
        }
        if (pointer.getRotate() == degree) {
          pointer.setDisable(true)
        }
        else {
          pointer.setRotate(angle)
        }
      }
    }
  }


}