import React, { useState, useEffect } from 'react';

const HeaderColumn = ({ dateFrom, dateTo, setFilteredCount }) => {
  const [expandedRows, setExpandedRows] = useState({});
  const [orders, setOrders] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [sortOrder, setSortOrder] = useState('asc');

  const toggleExpand = (id) => {
    setExpandedRows((prevState) => ({
      ...prevState,
      [id]: !prevState[id], // Toggle the expanded state of the row
    }));
  };

  useEffect(() => {


    const fetchOrders = async () => {
      try {
        const response = await fetch('http://localhost:3003/order');
        const result = await response.json();
        setOrders(result);
        setFilteredData(result);
        setFilteredCount(result.length);
      }
      catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchOrders();
  }, [setFilteredCount]);

  useEffect(() => {
    if (dateFrom && dateTo) {
      // Filter data based on date range
      const filtered = orders.filter((order) => {
        const orderDate = new Date(order.dateTime);
        const fromDate = new Date(dateFrom);
        const toDate = new Date(dateTo);

        return orderDate >= fromDate && orderDate <= toDate;
      });

      // Update filtered data and count
      setFilteredData(filtered);
      setFilteredCount(filtered.length); // Send count back to parent component
    } else {
      // If no date range is selected, show all data
      setFilteredData(orders);
      setFilteredCount(orders.length);
    }
  }, [orders, dateFrom, dateTo, setFilteredCount]);

  const handleSortByDate = () => {
    const sortedData = [...filteredData].sort((a, b) => {
      const dateA = new Date(a.dateTime);
      const dateB = new Date(b.dateTime);

      if (sortOrder === 'asc') {
        return dateA - dateB;
      } else {
        return dateB - dateA;
      }
    });

    setFilteredData(sortedData);
    setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
  };


  return (
    <div className="overflow-x-auto">
    <div className="grid grid-cols-12 gap-2 p-2 font-bold border-b">
      <div className="col-span-3 md:col-span-1">Account</div>
      <div className="col-span-3 md:col-span-1">Operation</div>
      <div className="col-span-3 md:col-span-1">Symbol</div>
      <div className="hidden md:block md:col-span-2">Description</div>
      <div className="hidden md:block md:col-span-1">Qty.</div>
      <div className="hidden md:block md:col-span-1">Filled Qty</div>
      <div className="hidden md:block md:col-span-1">Price</div>
      <div className="md:block md:col-span-1">Status</div>
      <div className="hidden md:block md:col-span-1"><button onClick={handleSortByDate}>Date {sortOrder === 'asc' ? '↑' : '↓'}</button></div>
      <div className="hidden md:block md:col-span-1">No. Ref.</div>
      <div className="hidden md:block md:col-span-1">Ext. Ref.</div>
      
    </div>
      {filteredData.map((order) => (
        <div key={order.id} className="border-b border-gray-200">
          <div className="grid grid-cols-12 gap-4 items-center py-2 cursor-pointer hover:bg-gray-100 text-sm" onClick={() => toggleExpand(order.id)}>
            <div className="col-span-3 md:col-span-1">
              <p className='text-blue-700'>{expandedRows[order.id] ? "▼" : "▶"}{order.account}</p>
            </div>
            <div className="col-span-3 md:col-span-1">{order.operation}</div>
            <div className="col-span-3 md:col-span-1">{order.symbol}</div>
            <div className="hidden md:block md:col-span-2">{order.description}</div>
            <div className="hidden md:block md:col-span-1">{order.qty}</div>
            <div className="hidden md:block md:col-span-1">{order.filedQty}</div>
            <div className="hidden md:block md:col-span-1">{order.price}</div>
            <div className="md:block md:col-span-1">{order.status}</div>
            <div className="hidden md:block md:col-span-1">{order.dateTime}</div>
            <div className="hidden md:block md:col-span-1">{order.referenceNo}</div>
            <div className="hidden md:block md:col-span-1">{order.externalReferenceNo}</div>
          </div>

          {expandedRows[order.id] && (
            <div className="p-4 bg-gray-50 border border-t-0 rounded-b-lg">
              <h3 className="text-left text-blue-700 font-medium">
                {order.details.firstName} {order.details.lastName} {order.details.margin}
              </h3>
              <div className="col-span-1 flex justify-end space-x-2">
                <button className="bg-blue-500 text-white px-6 py-1 rounded-full hover:bg-blue-600">Accept</button>
                <button className="bg-white border border-red-500 text-red-500 px-6 py-1 rounded-full hover:bg-red-100">Reject</button>
              </div>
              <div className='grid grid-cols-4 gap-4'>
                <div class="text-left text-sm text-gray-600">
                  <p>Net Amount: <strong className="text-black">{order.details.netAmount}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>Price: <strong className="text-black">{order.price}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>Exchange Rate: <strong className="text-black">{order.details.exchangeRate}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>O/S Limit: <strong className="text-black">{order.details.otOfStockLimit}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>Reference Number: <strong className="text-black">{order.referenceNo}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>Date/Time: <strong className="text-black">{order.dateTime}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>Telephone: <strong className="text-black">{order.details.telephoneNo}</strong></p>
                </div>
                <div class="text-left text-sm text-gray-600">
                  <p>User ID: <strong className="text-black">{order.details.userId}</strong></p>
                </div>
              </div>
              <div className="text-sm text-gray-600">
                {/* Warning Section */}
                <div className="mt-2">
                  <p className="text-left font-semibold text-red-600">Warning(s)</p>
                  <ul className="text-left list-disc list-inside text-sm">
                    <li>To trade this security in this account, a currency conversion will be made at the current rate.</li>
                    <li>A similar order has already been submitted.</li>
                    <li>Your transaction will be processed the following business day.</li>
                    <li>It is not possible to calculate the buying power of this order.</li>
                    <li>A cancellation will not be possible during business hours or market orders. You can call a representative for more information.</li>
                    <li>For the above-mentioned reason(s), your order will be processed by one of our representatives.</li>
                  </ul>
                </div>
              </div>
            </div>
          )}
        </div>
      ))}

    </div>
  );
};

export default HeaderColumn;
