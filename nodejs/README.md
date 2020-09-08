# Install Cucumber and Create a new Project
You will need NodeJs installed in your machine. After NodeJs is installed, create an empty folder and type:

    npm init --yes


This will start an empty project. Then install Cucumber:

    npm install cucumber --save-dev

In the newly created project, there will be a file called **package.json**. Add the Cucumber dependency:

    "scripts": {
        "test": "cucumber-js"
    }

## Create the folder structure

You will need to create the following folder structrure for the project:

    // root folder
    |
    +-features
    |  |
    |  +-step_definitions
    |
    +-node_modules
    |
    +-package.json
    +-.gitignore
    +- ...

## Create basic project files
Create the file **cucumber.js** and add the following content:

    module.exports = {
        default: `--format-options '{"snippetInterface": "synchronous"}'`
    }

And the very first Step definition script, inside the *step_definitions* folder:


    const assert = require('assert');
    const { Given, When, Then } = require('cucumber');

## Run

After the project structure is ready, run the tests by typing:

    npm test