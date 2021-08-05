package ch.makery.address.model
import scalafx.scene.image.{ImageView, Image}

class StovetopIngredients {
    //Spaghetti
  val boilingSpeget = new Image(getClass.getResourceAsStream("boilingSpeget.png"))
  val boilingSpegetImageView = new ImageView(boilingSpeget) {
    x = 162
    y = 244.5
  }
  val boilingSpeget1 = new Image(getClass.getResourceAsStream("boilingSpeget1.png"))
  val boilingSpeget1ImageView = new ImageView(boilingSpeget1) {
    x = 208
    y = 287.5
  }
  val boiledSpeget = new Image(getClass.getResourceAsStream("boiledSpeget.png"))
  val boiledSpegetImageView = new ImageView(boiledSpeget) {
    x = 226
    y = 335.5
  }

    //Cheese
    val parmesanBlock1 = new Image(getClass.getResourceAsStream("parmesanBlock1.png"))
    val parmesanBlock1ImageView = new ImageView(parmesanBlock1) {
        x = 915
        y = 220
    }
    val parmesanBlock2 = new Image(getClass.getResourceAsStream("parmesanBlock2.png"))
    val parmesanBlock2ImageView = new ImageView(parmesanBlock2) {
        x = 905
        y = 210
    }
    val parmesanBlock3 = new Image(getClass.getResourceAsStream("parmesanBlock3.png"))
    val parmesanBlock3ImageView = new ImageView(parmesanBlock3) {
        x = 890
        y = 215
    }

    //Cursor Images
  val oliveoilPour = new Image(getClass.getResourceAsStream("oliveoilPour.png"))
  val oliveoilPourImageView = new ImageView(oliveoilPour)
  val mincedItems = new Image(getClass.getResourceAsStream("mincedItems.png"))
  val mincedItemsImageView = new ImageView(mincedItems)
  val smokedPork = new Image(getClass.getResourceAsStream("smokedPork.png"))
  val smokedPorkImageView = new ImageView(smokedPork)
  val slicedMushrooms = new Image(getClass.getResourceAsStream("slicedMushrooms.png"))
  val slicedMushroomsImageView = new ImageView(slicedMushrooms)
  val whippingcreamPour = new Image(getClass.getResourceAsStream("whippingcreamPour.png"))
  val whippingcreamPourImageView = new ImageView(whippingcreamPour)
  val saltPour = new Image(getClass.getResourceAsStream("saltPour.png"))
  val saltPourImageView = new ImageView(saltPour)
  val pepperPour = new Image(getClass.getResourceAsStream("pepperPour.png"))
  val pepperPourImageView = new ImageView(pepperPour)
  val parsleyPour = new Image(getClass.getResourceAsStream("parsleyPour.png"))
  val parsleyPourImageView = new ImageView(parsleyPour)

  //Pan Ingredients
  val oilBase = new Image(getClass.getResourceAsStream("oilBase.png"))
  val oilBaseImageView = new ImageView(oilBase) {
    x = 785
    y = 405
  }
  val mincedonPan = new Image(getClass.getResourceAsStream("mincedonPan.png"))
  val mincedonPanImageView = new ImageView(mincedonPan) {
    x = 810
    y = 430
  }
  val porkonPan = new Image(getClass.getResourceAsStream("porkonPan.png"))
  val porkonPanImageView = new ImageView(porkonPan) {
    x = 790
    y = 420
  }
  val mushroomonPan = new Image(getClass.getResourceAsStream("mushroomonPan.png"))
  val mushroomonPanImageView = new ImageView(mushroomonPan) {
    x = 810
    y = 410
  }
  val pepperonPan = new Image(getClass.getResourceAsStream("pepperonPan.png"))
  val pepperonPanImageView = new ImageView(pepperonPan) {
    x = 810
    y = 420
  }
  val parsleyonPan = new Image(getClass.getResourceAsStream("parsleyonPan.png"))
  val parsleyonPanImageView = new ImageView(parsleyonPan) {
    x = 800
    y = 410
  }
  val pastaonPan = new Image(getClass.getResourceAsStream("pastaonPan.png"))
  val pastaonPanImageView = new ImageView(pastaonPan) {
    x = 780
    y = 400
  }
  val creamBase = new Image(getClass.getResourceAsStream("creamBase.png"))
  val creamBaseImageView = new ImageView(creamBase) {
    x = 780
    y = 395
  }
  val cookedBase = new Image(getClass.getResourceAsStream("cookedBase.png"))
  val cookedBaseImageView = new ImageView(cookedBase) {
    x = 810
    y = 405
  }
  val cookedBase1 = new Image(getClass.getResourceAsStream("cookedBase1.png"))
  val cookedBase1ImageView = new ImageView(cookedBase1) {
    x = 780
    y = 400
  }
  val cookedBase2 = new Image(getClass.getResourceAsStream("cookedBase2.png"))
  val cookedBase2ImageView = new ImageView(cookedBase2) {
    x = 780
    y = 400
  }
  val shreddedParmesan1 = new Image(getClass.getResourceAsStream("shreddedParmesan1.png"))
  val shreddedParmesan1ImageView = new ImageView(shreddedParmesan1) {
    x = 820
    y = 400
  }
  val shreddedParmesan2 = new Image(getClass.getResourceAsStream("shreddedParmesan2.png"))
  val shreddedParmesan2ImageView = new ImageView(shreddedParmesan2) {
    x = 790
    y = 400
  }
  val shreddedParmesan3 = new Image(getClass.getResourceAsStream("shreddedParmesan3.png"))
  val shreddedParmesan3ImageView = new ImageView(shreddedParmesan3) {
    x = 830
    y = 400
  }

  var hasOliveOil = false
  var hasMincedItems = false
  var hasPorkBelly = false
  var hasMushrooms = false
  var hasWhippingCream = false
  var hasSalt = false
  var hasPepper = false
  var hasParsley = false

}