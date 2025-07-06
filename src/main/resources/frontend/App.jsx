
import StockDashboard from './trading/StockDashboard';
import React, { useEffect, useState } from 'react';
import { getRules, createRule, evaluateRule } from './api/api';

function App() {
  const [rules, setRules] = useState([]);
  const [ruleForm, setRuleForm] = useState({ symbol: '', triggerPrice: '', action: '' });
  const [evaluationInput, setEvaluationInput] = useState('');
  const [evaluationResult, setEvaluationResult] = useState(null);

  useEffect(() => {
    loadRules();
  }, []);

  const loadRules = () => {
    getRules()
      .then(setRules)
      .catch(console.error);
  };

  const handleCreateRule = () => {
    console.log("Submitting rule:", ruleForm);
    createRule(ruleForm)
      .then(() => {
        setRuleForm({ symbol: '', triggerPrice: '', action: '' });
        loadRules();
      })
      .catch(err => {
        console.error("Error creating rule:", err);
      });
  };

  const handleEvaluate = () => {
    try {
      const input = JSON.parse(evaluationInput);
      evaluateRule(input)
        .then(setEvaluationResult)
        .catch(console.error);
    } catch (e) {
      alert('Please enter valid JSON');
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <StockDashboard />
      <h1>Rule Engine UI</h1>

      <h2>Create a Rule</h2>
      <input
        placeholder="Symbol"
        value={ruleForm.symbol}
        onChange={(e) => setRuleForm({ ...ruleForm, symbol: e.target.value })}
      />
      <input
        placeholder="Trigger Price"
        type="number"
        value={ruleForm.triggerPrice}
        onChange={(e) => setRuleForm({ ...ruleForm, triggerPrice: parseFloat(e.target.value) })}
      />
      <input
        placeholder="Action"
        value={ruleForm.action}
        onChange={(e) => setRuleForm({ ...ruleForm, action: e.target.value })}
      />
      <button onClick={handleCreateRule}>Add Rule</button>

      <h2>All Rules</h2>
      <ul>
        {rules.map((rule, index) => (
          <li key={index}>
            <strong>{rule.symbol}</strong>: if (price &gt; {rule.triggerPrice}) → {rule.action}
          </li>
        ))}
      </ul>

      <h2>Evaluate Input</h2>
      <textarea
        rows={5}
        cols={50}
        placeholder='Enter JSON input (e.g. {"symbol": "TCS", "price": 3600})'
        value={evaluationInput}
        onChange={(e) => setEvaluationInput(e.target.value)}
      />
      <br />
      <button onClick={handleEvaluate}>Evaluate</button>

      {evaluationResult && (
        <div>
          <h3>Evaluation Result</h3>
          <pre>{JSON.stringify(evaluationResult, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}

export default App;

