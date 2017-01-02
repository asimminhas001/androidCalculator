## androidCalculator
An Android calculator with a history functionality using SQLite.

Get the APK and install it on your device to try it out;
https://drive.google.com/file/d/0B7tOK_HzoD_yYmNZQ2VSUm9IUHM/view?usp=sharing

![Calculator Image](/app/src/main/res/drawable/calculator_screen_shot_jan_25.png)

### Getting Started
1. Clone the Project
2. Open in Android Studio
3. Make sure you are in master branch
4. Deploy to device or emulator

### Prerequisites

- Android Studio 2.* (latest)
- Some Android Experience or Basic Android tutorial although this is a decent app to learn from.

### Conductor Controller Lifecycle (source - [Conductor's README](https://github.com/bluelinelabs/Conductor/blob/develop/README.md))

The lifecycle of a Controller is significantly simpler to understand than that of a Fragment. A lifecycle diagram is shown below:

![Controller Lifecycle](https://github.com/bluelinelabs/Conductor/raw/develop/docs/Controller%20Lifecycle.jpg)

## Advanced Topics

### Retain View Modes
`setRetainViewMode` can be called on a `Controller` with one of two values: `RELEASE_DETACH`, which will release the `Controller`'s view as soon as it is detached from the screen (saves memory), or `RETAIN_DETACH`, which will ensure that a `Controller` holds on to its view, even if it's not currently shown on the screen (good for views that are expensive to re-create).

### Custom Change Handlers
`ControllerChangeHandler` can be subclassed in order to perform different functions when changing between two `Controllers`. Two convenience `ControllerChangeHandler` subclasses are included to cover most basic needs: `AnimatorChangeHandler`, which will use an `Animator` object to transition between two views, and `TransitionChangeHandler`, which will use Lollipop's `Transition` framework for transitioning between views.

### Child Routers & Controllers
`getChildRouter` can be called on a `Controller` in order to get a nested `Router` into which child `Controller`s can be pushed. This enables creating advanced layouts, such as Master/Detail.

### To Contribute:
1. Clone Project
2. Pick an issue and create branch with [Issue #]-dev (i.e. 3-dev for issue number 3)
3. Do stuff
4. Pull dev and merge with your branch to get rid of any conflicts from new changes by others
5. Create pull request to dev
6. Celebrate

### Author

Kamal Hamoud - Initial build

### License
This project is licensed under the MIT License - see the LICENSE.md file for details

### Acknowledgments

[navneetgarg123](https://github.com/navneetgarg123) - Calculator Algorithm
<br>
[MoeHamoud](https://github.com/MoeHamoud) - Android Architecture and Build
