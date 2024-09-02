import React from 'react';

const SearchHeader = ({ dateFrom, dateTo, setDateFrom, setDateTo, filteredCount }) => {
    const handleDateChange = (e) => {
        const { name, value } = e.target;
        if (name === 'dateFrom') setDateFrom(value);
        if (name === 'dateTo') setDateTo(value);
      };

    return (
        <div className="p-4 bg-white shadow rounded flex flex-col md:flex-row items-center gap-4">
            <div className='basis-1/2 text-left'>
                <h2 className="text-xl font-semibold">Search</h2>
                <span className="text-gray-500 text-left">Search results: <strong>{filteredCount}</strong></span>
            </div>
            <div className="basis-1/2 flex items-center gap-4">
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
                        name="dateFrom"
                        value={dateFrom}
                        onChange={handleDateChange}
                        placeholder="From Date"
                        className="mt-1 block w-full p-2 border rounded"
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700">To</label>
                    <input
                        type="date"
                        name="dateTo"
                        value={dateTo}
                        onChange={handleDateChange}
                        placeholder="To Date"
                        className="mt-1 block w-full p-2 border rounded"
                    />
                </div>

                <button onClick={handleDateChange} className="mt-4 md:mt-0 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                    Search
                </button>
            </div>
        </div>
    );
};

export default SearchHeader;
