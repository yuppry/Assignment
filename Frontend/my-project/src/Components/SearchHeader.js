import React, { useState } from 'react';

const SearchHeader = () => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  return (
    <div className="p-4 bg-white shadow rounded flex flex-col md:flex-row items-center gap-4">
      <h2 className="text-xl font-semibold">Search</h2>
      <span className="text-gray-500">Search results: <strong>???</strong></span>
      <div className="flex items-center gap-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">Period</label>
          <select className="mt-1 block w-full p-2 border rounded">
            <option>Transmission</option>
          </select>
        </div>
        
        <div>
          <label className="block text-sm font-medium text-gray-700">Status</label>
          <select className="mt-1 block w-full p-2 border rounded">
            <option>Waiting</option>
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">From</label>
          <input
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            className="mt-1 block w-full p-2 border rounded"
          />
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">To</label>
          <input
            type="date"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            className="mt-1 block w-full p-2 border rounded"
          />
        </div>

        <button className="mt-4 md:mt-0 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Search
        </button>
      </div>
    </div>
  );
};

export default SearchHeader;
