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

  // ✅ return text instead of json because backend returns plain string
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

















/*export const getRules = () =>
  fetch('http://localhost:8080/api/rules').then(res => res.json());

export const createRule = (rule) =>
  fetch('http://localhost:8080/api/rules', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rule)
  });

export const evaluateRule = (input) =>
  fetch('http://localhost:8080/api/rules/evaluate', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(input)
  }).then(res => res.json());*/
