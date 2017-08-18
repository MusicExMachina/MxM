#!/bin/bash
# This code is all borrowed from ReadyTalk/swt-bling, which is under an MIT license as of 8/13/2017
# Kindly provided as a part of Ben Limmer's tutorial on javadoc-ing with Travis CI on his blog at:
# https://benlimmer.com/2013/12/26/automatically-publish-javadoc-to-gh-pages-with-travis-ci/

if [ "$TRAVIS_REPO_SLUG" == "MusicExMachina/MxM" ] &&
   [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] &&
   [ "$TRAVIS_PULL_REQUEST" == "false" ]; then

  echo -e "Publishing javadoc...\n"

  cp -R build/docs/javadoc $HOME/javadoc-latest

  # Get to the Travis build directory, configure git and clone the repo
  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --branch=gh-pages https://${GH_TOKEN}@github.com/MusicExMachina/MxM gh-pages > /dev/null

  # Commit and push the changes
  cd gh-pages
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc-latest ./javadoc
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi