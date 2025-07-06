
import React, { useEffect, useState } from 'react';
import './StockDashboard.css';

const StockDashboard = () => {
  const [stocks, setStocks] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/dashboard/evaluate')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }
        return response.json();
      })
      .then((data) => {
        setStocks(data);
      })
      .catch((error) => {
        console.error('Error fetching stock data:', error);
        setStocks([]);
      });
  }, []);

  return (
    <div className="dashboard-container">
      <h2>📈 Auto Trading Dashboard</h2>
      <table className="stock-table">
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Current Price</th>
            <th>Trigger Price</th>
            <th>Action</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(stocks) && stocks.length > 0 ? (
            stocks.map((stock, index) => (
              <tr key={index}>
                <td>{stock.symbol}</td>
                <td>{stock.currentPrice}</td>
                <td>{stock.triggerPrice}</td>
                <td>{stock.action}</td>
                <td>
                  {stock.matched ? (
                    <span className="status-triggered">✅ Triggered</span>
                  ) : (
                    <span className="status-not-triggered">⛔ Not Triggered</span>
                  )}
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No data available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default StockDashboard;

