import React from 'react';

const SearchHeader = ({ dateFrom, dateTo, setDateFrom, setDateTo, filteredCount }) => {
    const handleDateChange = (e) => {
        const { name, value } = e.target;
        if (name === 'dateFrom') setDateFrom(value);
        if (name === 'dateTo') setDateTo(value);
    };

    return (
        <div className="p-4 bg-white shadow rounded-lg mb-4">
            <div className="flex flex-col flex-wrap md:flex-row md:items-center md:justify-between gap-4">
                <div className="flex flex-col flex-auto sm:flex-row gap-2">
                    <h2 className="mt-4 text-xl font-semibold ">Search</h2>
                    {/* filteredCount */}
                    <span className="mt-5 font-semibold text-gray-500">Search results: <strong>{filteredCount}</strong></span>
                </div>

                <div className="hidden flex flex-col sm:flex-row gap-2">
                    <label className="mt-3">Period</label>
                    <select className="mt-1 block w-full p-2 border rounded">
                        <option>Transmission</option>
                    </select>
                </div>

                <div className="hidden flex flex-col sm:flex-row gap-2">
                    <label className="mt-3">Status</label>
                    <select className="mt-1 block w-full p-2 border rounded">
                        <option>Waiting</option>
                    </select>

                </div>

                {/* Date Range Inputs */}
                <div className="flex flex-col sm:flex-row gap-2">
                    <label className='mt-3'>From</label>
                    <input
                        type="date"
                        name="dateFrom"
                        value={dateFrom}
                        onChange={handleDateChange}
                        placeholder="From Date"
                        className="border rounded p-2 w-full sm:w-auto"
                    />
                    <label className='mt-3'>To</label>
                    <input
                        type="date"
                        name="dateTo"
                        value={dateTo}
                        onChange={handleDateChange}
                        placeholder="To Date"
                        className="border rounded p-2 w-full sm:w-auto"
                    />
                </div>

                {/* Search Button */}
                <div>
                    <button className="bg-blue-500 text-white px-4 py-2 rounded w-full sm:w-auto hover:bg-blue-600">
                        Search
                    </button>
                </div>
            </div>

        </div>
    );
};

export default SearchHeader;
