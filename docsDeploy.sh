!/bin/sh

echo "Beginning documentation generation"

# Exit with error code in the event of a failure
set -e

# Enter doxygen directory
cd doxygen

# Set up git
git config --global push.default simple
git config user.name "Travis CI"
git config user.email "travis@travis-ci.org"

# Delete the current documentation directory
rm -rf ./docs

echo 'Invoking doxygen'
# Invoke doxygen with the Doxyfile in this directory, redirecting output to a log file
doxygen 2>&1 | tee doxygen.log

# Ensure that doxygen correctly generated the documentation
if [ -d "docs" ] && [ -f "docs/index.html" ]; then
  echo 'Pushing documentation to master'
  git add docs
  git commit -m "Regenerate documentation" -m "Travis build: ${TRAVIS_BUILD_NUMBER}" -m "Commit: ${TRAVIS_COMMIT}"
  git push
else
  echo 'Error: doxygen was unable to generate the documentation!' >&2
  exit 1
fi
