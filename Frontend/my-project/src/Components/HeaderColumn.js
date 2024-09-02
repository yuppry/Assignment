import React, { useState, useEffect } from 'react';

const HeaderColumn = () => {
  const [expandedRows, setExpandedRows] = useState({});
  const [orders, setOrders] = useState([]);
  const [dataCount, setDataCount] = useState(0);

  const toggleExpand = (id) => {
    setExpandedRows((prevState) => ({
      ...prevState,
      [id]: !prevState[id], // Toggle the expanded state of the row
    }));
  };

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const response = await fetch('http://localhost:3003/order');
      const data = await response.json();
      setOrders(data);

    }
    catch (error) {
      console.error('Error fetching data:', error);
    }
  };


  return (
    <div className="p-4 bg-white shadow rounded-lg">
      <div className="grid grid-cols-12 gap-4 py-2 text-sm font-semibold border-b">
        <div className="col-span-1">Account</div>
        <div className="col-span-1">Operation</div>
        <div className="col-span-1">Symbol</div>
        <div className="col-span-2">Description</div>
        <div className="col-span-1">Qty.</div>
        <div className="col-span-1">Filled Qty</div>
        <div className="col-span-1">Price</div>
        <div className="col-span-1">Status</div>
        <div className="col-span-1">Date</div>
        <div className="col-span-1">No. Ref.</div>
        <div className="col-span-1">Ext. Ref.</div>
      </div>
      {orders.map((order) => (
        <div key={order.id} className="border-b border-gray-200">
          <div className="grid grid-cols-12 gap-4 items-center py-2 cursor-pointer hover:bg-gray-100" onClick={() => toggleExpand(order.id)}>
            <div className="col-span-1 flex items-center">
              <span className="mr-2 ">▼</span>
              <p className='text-blue-700'>{order.account}</p>
            </div>

            <div className="col-span-1">{order.operation}</div>
            <div className="col-span-1">{order.symbol}</div>
            <div className="col-span-2">{order.description}</div>
            <div className="col-span-1">{order.qty}</div>
            <div className="col-span-1">{order.filledQty}</div>
            <div className="col-span-1">{order.price}</div>
            <div className="col-span-1">{order.status}</div>
            <div className="col-span-1">{order.dateTime}</div>
            <div className="col-span-1">{order.referenceNo}</div>
            <div className="col-span-1">{order.externalReferenceNo}</div>
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
