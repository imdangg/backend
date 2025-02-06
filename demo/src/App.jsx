import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from "./components/Home.jsx";
import MyExchange from './components/MyExchange.jsx';
import MyInsight from './components/MyInsight.jsx';
import DetailInsight from './components/DetailInsight.jsx'
import CreateInsight from "./components/CreateInsight.jsx";

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/my-exchange" element={<MyExchange />} />
                <Route path="/my-insight" element={<MyInsight />} />
                <Route path="/insight/:id" element={<DetailInsight />} />
                <Route path="/insight/create" element={<CreateInsight />} />
            </Routes>
        </Router>
    );
}
