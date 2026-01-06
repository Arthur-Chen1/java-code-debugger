# Java Code Debugger

## Overview
Java Code Debugger is a personal project that analyzes Java code errors and provides helpful debugging suggestions using the OpenAI API. It includes a backend built with Java and Spring Boot, and a frontend built with React.

## Motivation
This was my first independent project built almost entirely on my own. It tested my persistence, problem-solving, and developed my ability to integrate APIs and frontend frameworks into a fully functioning tool.

## Features
- Submit Java code and receive debugging insights
- Frontend code editor using CodeMirror
- REST API endpoints for testing and code analysis
- Fully independent implementation

## Technologies
**Backend:** Java, Spring Boot, OpenAI API, Java Compiler API  
**Frontend:** React, Material UI, CodeMirror  
**Build/Tools:** Maven, Node.js, ESLint  

## Setup Instructions

### Backend
1. Set your OpenAI API key as an environment variable:
  ```bash
  # Linux / macOS
  export OPENAI_API_KEY="your_key_here"

  # Windows (PowerShell)
  setx OPENAI_API_KEY "your_key_here"
  ```
2. Start the backend server:
  ```bash
  cd chat-ai-backend
  mvn spring-boot:run
  ```

### Frontend
1. Install dependencies:
  ```bash
  cd chat-ai-frontend
  npm install
  ```
2. Start the frontend:
  ```bash
  npm start
  ```

## API Endpoints:
  ```text
  GET /test/ping --> returns "Backend is running!"
  POST /test/echo --> echoes the JSON payload you send
  ```

## Notes
The OpenAI API key is never committed to GitHub; it must be set as an environment variable.
All code is built independently.
Demonstrates skills in debugging, API integration, and frontend-backend coordination.

