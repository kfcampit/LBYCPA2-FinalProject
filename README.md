 # LBYCPA2 Final Project

**Members:** Kyle Campit, Jian Mendoza, and Kobi Rasing

## To-do List

### Pages

- Main Menu
- Answer Quiz
- Edit Quiz
- Create Quiz
- View Statistics

### Back-end

- Firebase Read and Write
- Statistics
- Custom Question Object

#
## Cloud Firestore Structure


### Sample Cloud Firestore Structure
```
quizzes
    |- id: "0001-basic-math"
    |           |- topic: "Basic Mathematics"
    |           |- questions
    |                   |- id: "q001"
    |                   |       |- question: "What is 1+1?"
    |                   |       |- choices:
    |                   |       |       |- 0: One
    |                   |       |       |- 1: Two
    |                   |       |       |- 2: Three
    |                   |       |       |- 3: Four
    |                   |       |
    |                   |       |- answer: 1
    |                   |       |- weight: 5
    |                   |       |- imageURL: "https://image1.jpg"
    |                   |       
    |                   |- id: "q002"
    |                           |- question: "What is 2+2?"
    |                           |- choices:
    |                           |       |- 0: One
    |                           |       |- 1: Two
    |                           |       |- 2: Three
    |                           |       |- 3: Four
    |                           |
    |                           |- answer: 3
    |                           |- weight: 10
    |                           |- imageURL: "https://image2.jpg"
    |
    |- id: "0002-basic-physics"
                |- ... and so on
```
Each quiz is given a unique id in the format of "0000-title". Under each quiz, there is a topic field which stores a String of the topic of the quiz. Each quiz also has a collection of questions. This collection contains documents representing questions. Each question has an id in the format of "q000".

Each question has five fields. There as a question field and imageURL field which store a String; a choices field which stores a String array; and an answer and weight field which stores Integers.

### Database Standards
- The id of a new quiz MUST be in the format of ``` "0000-name" ```. *Ex. "0001-basic-math", "0002-calculus", and "0003-data-structures-and-algorithms"*
- The id of a new question MUST be in the format of ``` "q000" ```. *Ex. "q001", "q002", and "q019"*
- The number of choices for each question MUST always be 4.
- The answer field under each question is the INDEX of the correct answer from the choices array. The array begins from 0.
- The URL link for the image MUST have ``` "https://" ``` in the beginning.

#
## Custom Objects

Using the QuizObject and the QuestionObject will be the primary ways to interact with the contents of the quiz. They encapsulate the data and make it easier and simplier to interact with the quiz data and Cloud Firestore.

### QuestionObject
``` Java 8
public interface QuestionInterface {
    void setQuestion(String question);
    String getQuestion();

    void setChoices(String[] choices);
    String[] getChoices();

    void setAnswer(int index);
    int getAnswer();

    void setPointWeight(int pointWeight);
    int getPointWeight();

    void setImageURL(String url);
    String getImageURL();
}
```

The QuestionObject contains setters and getters for the all of the relevant fields that each question must have. It stores Strings, Integers, and an Array. Each new instance of the QuestionObject is a new question.

### QuizObject
```Java 8
public interface QuizInterface {
    void setTopic(String topic);
    String getTopic();

    void setID(String id);
    String getID();
    
    void setQuestions(List<QuestionObject> questions);
    QuestionObject getNextQuestion();
    QuestionObject getRandomQuestion();
    List<QuestionObject> getQuestionList();

    int getNumberQuestions();
    int getTotalPoints();
}
```

The QuizObject also contains setters and getters for some of the fields for each quiz. The class contains a list of QuestionObjects as one of these fields. This is simply because each quiz contians a list of questions, which in turn consists of its own fields. The getters of the questions are also different. The get question commands could either be in the form of getting the next question, getting a random question from the list, or getting a list of all the questions.

#
## Using Firebase Implementation

Make sure to instantiate the QuizController in the class to access the quizzes and questions in the Cloud Firestore.
``` Java 8
QuizController qc = new QuizController();
```
### Getting Topics
``` Java 8
public Hashtable<String, String> getTopics()
//Output: {0001-basic-math = Basic Mathematics, 0002-basic-physics = Basic Physics}
```
The getTopics command returns a Hashtable of the id and topic name of all the available quizzes. The key for each hash is the id and the value is the topic name. The command can be used when listing out all the available quiz topics in the program

### Getting a Quiz
``` Java 8
public QuizObject getQuiz(String id)
//Sample Usage: QuizObject quiz = getQuiz("0001-basic-math");
```
The getQuiz command returns the custom QuizObject. The contents of this QuizObject comes directly from the Cloud Firestore. This object will be the one used in the actual program.

### Adding and Editing a Quiz
``` Java 8
public void manageQuiz(QuizObject quizObject)
/*
Sample Usage:
quiz.setTopic("Basic Math");
manageQuiz(quiz);
*/
```
Using overwriting, adding a new quiz and editing an existing quiz share the same command. As long as the all edits in a quiz is done through the custom quiz object, the manageQuiz command can handle both adding and editing quizzes. Make sure all paramaters and fields are completed (The imageURl can be empty) before calling this command.

### Removing a Quiz
``` Java 8
public void removeQuiz(String quizID)
//Sample Usage: removeQuiz("0001-basic-math");
```
This command simply removes all contents of the quiz by deleting its collections and documents.

#
## Using Cloud Image Hosting

Make sure to instantiate the ImageContoller in the class to access the Cloudinary image hosting service.
``` Java 8
ImageController ic = new ImageController();
```

### Saving Images
``` Java 8
public void saveImage(String filename, String quizID)
//Sample Usage: saveImage("q001.jpg", "0001-basic-math");
```

The saveImage command will upload the image to the Cloudinary image hosting platform under the folder of the quizID. The filename of the image must be the same as the question ID. The file type must also be a .jpg.

### Getting Image URL
``` Java 8
public String loadImageURL(String filename, String quizID)
/*
Sample Usage: loadImageURL("q001.jpg", "0001-basic-math");
Sample Output: http://res.cloudinary.com/de-la-salle-university-manila/image/upload/v1/0001-basic-math/q001.jpg
*/
```
The loadImageURL command will access the image hosting library and retriev the indicated image based of its file name and quizID. The return value for the command is a String containing the URL of the image.