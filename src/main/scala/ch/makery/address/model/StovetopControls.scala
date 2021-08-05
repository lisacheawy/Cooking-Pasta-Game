package ch.makery.address.model
class StovetopControls {
    var isSwap = false //for swapping fire images (1st stove)
  var isSwap1 = false //for swapping fire images (2nd stove)
  var isSwap2 = false //for swapping water images
  var isSwap3 = false //for smoke on pan
  var isBoiled = false //for boiling water state
  var cookStart = false
  var spegetisBoiling = false
  var firstCookDone = false
  var secondCookDone = false
  var thirdCookDone = false
  var boilAudioStart = false
  var isDone = false
  var moveCounter = 30

  //Shake the pan
  var shakeCounter = 0
  var panCounter = 0
  var movePan = false

  //Grate parmesan
  var grateCounter = 0
  var parmesanCounter = 0
  var moveParmesan = false
  var dragCounter = 70
}