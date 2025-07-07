const BASE_URL = 'http://localhost:8080/api'; 

export async function getRules() {
  const response = await fetch(`${BASE_URL}/rules`);
  return response.json();
}

export async function createRule(rule) {
  const response = await fetch(`${BASE_URL}/rules`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rule),
  });

  return response.text(); 
}

export async function evaluateRule(input) {
  const response = await fetch(`${BASE_URL}/rules/evaluate`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(input),
  });
  return response.json();
}


















 
