import React, { useState } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import { java } from '@codemirror/lang-java';
import { Alert, AlertTitle, CircularProgress, Button } from '@mui/material';
import { Decoration, ViewPlugin } from '@codemirror/view';
import ErrorOutlineIcon from '@mui/icons-material/ErrorOutline';
import TipsAndUpdatesIcon from '@mui/icons-material/TipsAndUpdates';
import './App.css'; 

function getLineDecorations(errorLines) {
  return ViewPlugin.fromClass(class {
    constructor(view) {
      this.decorations = Decoration.set(
        errorLines.map(line => {
          const from = view.state.doc.line(line).from;
          return Decoration.line({
            attributes: { class: 'error-line' }
          }).range(from);
        })
      );
    }
    update(update) {}
  }, {
    decorations: v => v.decorations
  });
}

function CodeAnalyzer() {
  const [code, setCode] = useState('');
  const [errors, setErrors] = useState([]);
  const [aiExplanation, setAiExplanation] = useState('');
  const [loading, setLoading] = useState(false);
  const [errorLines, setErrorLines] = useState([]);

  const analyzeCode = async () => {
  setLoading(true);
  try {
    const response = await fetch('http://localhost:8080/api/analyze', {
      method: 'POST',
      headers: { 'Content-Type': 'text/plain' },
      body: code,
    });
    const data = await response.json();
    setErrors(data.errors || []);
    setAiExplanation(data.aiExplanation || '');
    setErrorLines((data.errors || []).map(err => err.lineNumber));
  } catch (error) {
    console.error('Error:', error);
    setAiExplanation('Failed to fetch from backend.');
  }
  setLoading(false);
};

  return (
    <div style={{ padding: '2rem' }}>
  <h1>AI Code Analyzer</h1>

  <CodeMirror
    value={code}
    height="300px"
    extensions={[java(), getLineDecorations(errorLines)]}
    onChange={val => setCode(val)}
  />

  <br />
  <Button variant="contained" onClick={analyzeCode} disabled={loading}>
    {loading ? <CircularProgress size={24} color="inherit" /> : 'Analyze'}
  </Button>

  {errors.length > 0 && (
    <Alert severity="error" icon={<ErrorOutlineIcon />} sx={{ mt: 3 }}>
      <AlertTitle>Compiler Errors</AlertTitle>
      <ul>
        {errors.map((err, idx) => (
          <li key={idx}>
            Line {err.lineNumber}: {err.message}
          </li>
        ))}
      </ul>
    </Alert>
  )}

  {aiExplanation && (
    <Alert severity="info" icon={<TipsAndUpdatesIcon />} sx={{ mt: 3 }}>
      <AlertTitle>AI Suggestions</AlertTitle>
      <pre>{aiExplanation}</pre>
    </Alert>
  )}
</div>
  );
}

export default CodeAnalyzer;