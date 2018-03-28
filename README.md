# Lantern

Lantern is the implementation of a machine learning framework prototype in [Scala](http://scala-lang.org/). The design of Lantern is built on two important and well-studied programming language concepts, [delimited continuations](http://web.cecs.pdx.edu/~apt/icfp09_accepted_papers/113.html) and multi-stage programming ([staging](https://scala-lms.github.io/) for short). Delimited continuations provides a very concise view of the reverse mode automated differentiation, which which permits implementing reverse-mode AD purely via operator overloading and without any auxiliary data structures. Multi-stage programming leading to a highly efficient implementation that combines the performance benefits of deep learning frameworks based on explicit reified computation graphs (e.g., [TensorFlow](https://www.tensorflow.org/)) with the expressiveness of pure library approaches (e.g., [PyTorch](http://pytorch.org/)).

This implementation is the companion artifact of our ICFP18 submission [Demystifying Differentiable Programming: Shift/Reset the Penultimate Backpropagator](https://arxiv.org). 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

[JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[sbt](https://www.scala-sbt.org/1.0/docs/)

### Directory Organization
* [root directory for Lantern](./src)
  * [Lantern code directory](./src/main/scala/lantern)
    * [code for AD on scalar variable](./src/main/scala/lantern/ad_lms.scala)
    * [code for AD on vector and tensor](./src/main/scala/lantern/ad_lms_vector.scala)
    * [LMS framework code](./src/main/scala/lantern/dslapi.scala)
    * [data loader](./src/main/scala/lantern/scanner.scala)
  * [Lantern test directory](./src/test/scala/lantern)
      * [ScalaTest instance of RNN evaluation](./src/test/scala/lantern/vanillaRNN.scala)
      * [ScalaTest instance of LSTM evaluation](./src/test/scala/lantern/LSTM.scala)
      * [ScalaTest instance of TreeLSTM evaluation](./src/test/scala/lantern/sentimentTreeLSTM.scala)
      * [ScalaTest instance of CNN evaluation](./src/test/scala/lantern/mnistCNN.scala)
  * [directory for evaluation code](./src/out/ICFP18evaluation)
      * [directory for RNN evaluation code](./src/out/ICFP18evaluation/evaluationRNN)
      * [directory for LSTM evaluation code](./src/out/ICFP18evaluation/evaluationLSTM)
      * [directory for TreeLSTM evaluation code](./src/out/ICFP18evaluation/evaluationTreeLSTM)
      * [directory for CNN evaluation code](./src/out/ICFP18evaluation/evaluationCNN)
      * [script for evaluation](./src/out/ICFP18evaluation/run_exp.sh)
      * [directory for evaluation results](./src/out/ICFP18evaluation/save_fig/)
      
### Compile deep learning models to C++ programs

Once you have cloned this repo, enter into the root directory of Lantern repo ($PATH_REPO/).

If you want to compile our demo code, execute:

```
$ sbt
sbt> testOnly lantern.$TEST_instance
```

Here $TEST_instance can be one of the following 4 test instances:
* VanillaRNN
* LSTMTest
* SentimentTreeLSTM
* MnistCNN

You can also choose to run all 4 test cases in one command:

```
$ sbt
sbt> test
```

All generated C++ code will be put in corresponding subdirectory under the directory for evaluation code (./src/out/ICFP18evaluation/).

## Running the deep learning model

Once you have compiled the deep learning model that you want to try, the C++ code is in corresponding directory. All you need is to compile that C++ program and run it. For example, suppose you are about to play with the Vanilla RNN language model and you already compiled the model and heve the generated code in directory. You can take the following steps to train it:

```
## make sure you are in the root directory of repo
cd ./src/out/ICFP18evaluation/evaluationRNN/
g++ -std=c++11 -O3 -march=native -Wno-pointer-arith Lantern.cpp -o Lantern
./Lantern result.txt
```

## Running the evaluations and plotting results

Running evaluations for CNN and TreeLSTM can take long time. We suggest users try VanillaRNN and LSTMTest first.

To run all test cases and plot their results, users only need to change working directory to repo and execute the following commands:

```
## suppose you already have all 4 models compiled
## make sure you are in the root directory of repo
cd ./src/out/ICFP18evaluation/
./run_exp.sh
```

The resulting plots are generated in the $PATH_REPO/src/out/ICFP18evaluation/save_fig/ dir.
